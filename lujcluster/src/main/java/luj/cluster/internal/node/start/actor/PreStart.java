package luj.cluster.internal.node.start.actor;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import java.util.function.Consumer;
import luj.cluster.api.node.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.AppRootAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2Factory;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;
import luj.cluster.internal.node.member.NodeMemberAktor;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;
import luj.cluster.internal.node.message.send.actor.NodeSendAktor;
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

    ActorRef appRootRef = createAppRoot();
    ActorRef sendRef = createSendActor();

    ActorRef receiveRef = createReceiveActor(sendRef, appRootRef);
    _receiveRefHolder.accept(receiveRef);

    //FIXME: 要在receiveActor完全启动后才能创建memberActor，不然在clusterJoin发的消息可能会因为太快到达，
    //       receiveActor还没启动完成而无法处理
    createMemberActor(receiveRef);

    StartContextImpl context = new StartContextImpl(receiveRef, sendRef, appRootRef, _aktor, null);
    for (NodeStartListener listener : _beanCollect.getStartListeners()) {
      listener.onStart(context);
    }
  }

  private ActorRef createAppRoot() {
    ActorMetaMap actorMetaMap = ActorMetaMap.Factory.create(_beanCollect);
    return _aktorCtx.actorOf(AppRootAktor.props(actorMetaMap), "approot");
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

  private void createMemberActor(ActorRef receiveRef) {
    Cluster cluster = Cluster.get(_aktorCtx.system());

    _aktorCtx.actorOf(NodeMemberAktor.props(cluster,
        _beanCollect.getMemberUpListener(), receiveRef, _applicationBean), "member");
  }

  private final NodeStartAktor _aktor;

  private final ActorContext _aktorCtx;
  private final ClusterBeanCollector.Result _beanCollect;

  private final Consumer<ActorRef> _receiveRefHolder;
  private final Object _applicationBean;
}
