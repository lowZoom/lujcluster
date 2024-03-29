package luj.cluster.internal.node.start.actor;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.Map;
import java.util.function.Consumer;
import luj.cluster.api.node.message.NodeMessageSerializer;
import luj.cluster.internal.node.appactor.akka.root.AppRootAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2Factory;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;
import luj.cluster.internal.node.member.actor.NodeMemberAktor;
import luj.cluster.internal.node.member.actor.message.StartMemberMsg;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;
import luj.cluster.internal.node.message.send.actor.NodeSendAktor;
import luj.cluster.internal.node.message.serialize.AkkaSerializeInitializer;
import luj.cluster.internal.node.message.serialize.MessageSerializerCollector;
import luj.cluster.internal.node.shutdown.ShutdownListenRegister;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import luj.cluster.internal.node.start.actor.trigger.StartListenTrigger;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

final class PreStart {

  PreStart(NodeStartAktor aktor, ActorContext aktorCtx, ClusterBeanCollector.Result beanCollect,
      ClusterNodeStarter.Config nodeConfig, boolean clusterEnabled,
      Consumer<ActorRef> receiveRefHolder) {
    _aktor = aktor;
    _aktorCtx = aktorCtx;
    _beanCollect = beanCollect;
    _nodeConfig = nodeConfig;
    _clusterEnabled = clusterEnabled;
    _receiveRefHolder = receiveRefHolder;
  }

  void run() throws Exception {
    LoggingAdapter log = Logging.getLogger(_aktor);
    log.debug("[cluster]节点开始启动...");

    Map<String, NodeMessageSerializer<?>> codecMap = new MessageSerializerCollector(
        _beanCollect.getNodeMessageSerializers()).collect();

    Object startParam = _nodeConfig.startParam();
    new AkkaSerializeInitializer(_beanCollect, codecMap, startParam).init();

    ActorRef memberRef = _aktorCtx.actorOf(
        NodeMemberAktor.props(codecMap, _beanCollect, _nodeConfig), "member");

    ActorRef appRootRef = createAppRoot(memberRef);
    ActorRef sendRef = createSendActor();

    ActorRef receiveRef = createReceiveActor(sendRef, appRootRef, codecMap);
    _receiveRefHolder.accept(receiveRef);

    // 注册关闭监听
    new ShutdownListenRegister(_beanCollect.getNodeShutdownListeners(),
        _aktor.context().system(), startParam).register();

    // 这里面可能也会包含初始化逻辑
    new StartListenTrigger(receiveRef, sendRef, appRootRef,
        startParam, _beanCollect.getNodeStartListeners()).trigger();

    // 所以需要在最后（即初始化完全完成）再加入集群
    memberRef.tell(new StartMemberMsg(receiveRef, _clusterEnabled, _nodeConfig), _aktor.self());
  }

  private ActorRef createAppRoot(ActorRef memberRef) {
    ActorMetaMap actorMetaMap = ActorMetaMap.Factory.create(_beanCollect);
    return _aktorCtx.actorOf(AppRootAktor.props(actorMetaMap, memberRef), "approot");
  }

  private ActorRef createSendActor() {
    return _aktorCtx.actorOf(NodeSendAktor.props(), "send");
  }

  private ActorRef createReceiveActor(ActorRef sendRef, ActorRef appRootRef,
      Map<String, NodeMessageSerializer<?>> codecMap) {
    ActorMessageHandleMapV2 handleMap = new ActorMessageHandleMapV2Factory(
        _beanCollect.getActorMessageHandlers()).create();

    Props prop = NodeReceiveAktor.props(handleMap, codecMap, sendRef, appRootRef);
    return _aktorCtx.actorOf(prop, "recv");
  }

  private final NodeStartAktor _aktor;
  private final ActorContext _aktorCtx;

  private final ClusterBeanCollector.Result _beanCollect;
  private final ClusterNodeStarter.Config _nodeConfig;
  private final boolean _clusterEnabled;

  private final Consumer<ActorRef> _receiveRefHolder;
}
