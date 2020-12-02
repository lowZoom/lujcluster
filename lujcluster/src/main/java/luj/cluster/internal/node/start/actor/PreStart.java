package luj.cluster.internal.node.start.actor;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.function.Consumer;
import luj.cluster.internal.node.appactor.akka.root.AppRootAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2Factory;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;
import luj.cluster.internal.node.member.actor.NodeMemberAktor;
import luj.cluster.internal.node.member.message.StartMemberMsg;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;
import luj.cluster.internal.node.message.send.actor.NodeSendAktor;
import luj.cluster.internal.node.start.actor.trigger.StartListenerTrigger;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

final class PreStart {

  PreStart(NodeStartAktor aktor, ActorContext aktorCtx, ClusterBeanCollector.Result beanCollect,
      Consumer<ActorRef> receiveRefHolder, Object applicationBean) {
    _aktor = aktor;
    _aktorCtx = aktorCtx;
    _beanCollect = beanCollect;
    _receiveRefHolder = receiveRefHolder;
    _applicationBean = applicationBean;
  }

  void run() throws Exception {
    LoggingAdapter log = Logging.getLogger(_aktor);
    log.debug("[cluster]节点开始启动...");

    ActorRef memberRef = _aktorCtx.actorOf(NodeMemberAktor.props(
        _beanCollect.getNodeJoinListener(), _applicationBean), "member");

    ActorRef appRootRef = createAppRoot(memberRef);
    ActorRef sendRef = createSendActor();

    ActorRef receiveRef = createReceiveActor(sendRef, appRootRef);
    _receiveRefHolder.accept(receiveRef);

    // 这里面可能也会包含初始化逻辑
    new StartListenerTrigger(receiveRef, sendRef, appRootRef,
        _applicationBean, _beanCollect.getNodeStartListeners()).trigger();

    // 所以需要在最后（即初始化完全完成）再加入集群
    memberRef.tell(new StartMemberMsg(receiveRef), _aktor.self());
  }

  private ActorRef createAppRoot(ActorRef memberRef) {
    ActorMetaMap actorMetaMap = ActorMetaMap.Factory.create(_beanCollect);
    return _aktorCtx.actorOf(AppRootAktor.props(actorMetaMap, memberRef), "approot");
  }

  private ActorRef createSendActor() {
    return _aktorCtx.actorOf(NodeSendAktor.props(), "send");
  }

  private ActorRef createReceiveActor(ActorRef sendRef, ActorRef appRootRef) {
    ActorMessageHandleMapV2 handleMap = new ActorMessageHandleMapV2Factory(
        _beanCollect.getActorMessageHandlers()).create();

    Props prop = NodeReceiveAktor.props(handleMap, sendRef, appRootRef);
    return _aktorCtx.actorOf(prop, "recv");
  }

  private final NodeStartAktor _aktor;

  private final ActorContext _aktorCtx;
  private final ClusterBeanCollector.Result _beanCollect;

  private final Consumer<ActorRef> _receiveRefHolder;
  private final Object _applicationBean;
}
