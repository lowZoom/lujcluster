package luj.cluster.internal.node.start.actor;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.event.DiagnosticLoggingAdapter;
import akka.event.Logging;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.api.node.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.AppRootAktor;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;
import luj.cluster.internal.node.member.NodeMemberAktor;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;
import luj.cluster.internal.node.message.send.actor.NodeSendAktor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

final class PreStart {

  PreStart(NodeStartAktor aktor, ActorContext aktorCtx, ClusterBeanCollector.Result beanCollect) {
    _aktor = aktor;

    _aktorCtx = aktorCtx;
    _beanCollect = beanCollect;
  }

  void run() throws Exception {
    DiagnosticLoggingAdapter log = Logging.getLogger(_aktor);
    log.debug("节点开始启动...");

    ActorRef appRootRef = createAppRoot();
    ActorRef sendRef = createSendActor();
    ActorRef receiveRef = createReceiveActor(sendRef, appRootRef);

    createMemberActor();

    ContextImpl context = new ContextImpl(receiveRef, sendRef, appRootRef, _aktor, null);
    for (NodeStartListener listener : _beanCollect.getStartListeners()) {
      listener.onStart(context);
    }
  }

  private ActorRef createAppRoot() {
    ActorMetaMap actorMetaMap = ActorMetaMap.Factory.create(_beanCollect);
    return _aktorCtx.actorOf(AppRootAktor.props(actorMetaMap), "approot");
  }

  private ActorRef createSendActor() {
    return _aktorCtx.actorOf(NodeSendAktor.props());
  }

  private ActorRef createReceiveActor(ActorRef sendRef, ActorRef appRootRef) {
    NodeMessageListener messageListener = _beanCollect.getMessageListener();
    Props prop = NodeReceiveAktor.props(messageListener, sendRef, appRootRef);
    return _aktorCtx.actorOf(prop, "recv");
  }

  private void createMemberActor() {
    Cluster cluster = Cluster.get(_aktorCtx.system());
    Props prop = NodeMemberAktor.props(cluster, _beanCollect.getMemberUpListener());
    _aktorCtx.actorOf(prop, "member");
  }

  private final NodeStartAktor _aktor;

  private final ActorContext _aktorCtx;
  private final ClusterBeanCollector.Result _beanCollect;
}
