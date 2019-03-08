package luj.cluster.internal.node.start.actor;

import akka.actor.ActorRef;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.api.start.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.AppRootAktor;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;
import luj.cluster.internal.node.message.send.actor.NodeSendActor;

final class PreStart {

  PreStart(NodeStartActor actor) {
    _actor = actor;
  }

  void run() throws Exception {
    System.out.print("[");
    System.out.print(Thread.currentThread().getName());
    System.out.println("] akka启动");

    ActorRef appRootRef = createAppRoot();

    ActorRef sendRef = createSendActor();
    ActorRef receiveRef = createReceiveActor(sendRef, appRootRef);

    ContextImpl context = new ContextImpl(receiveRef, sendRef, null);
    for (NodeStartListener listener : _actor.getCollectResult().getStartListeners()) {
      listener.onStart(context);
    }
  }

  private ActorRef createAppRoot() {
    return _actor.context().actorOf(AppRootAktor.props());
  }

  private ActorRef createSendActor() {
    return _actor.context().actorOf(NodeSendActor.props());
  }

  private ActorRef createReceiveActor(ActorRef sendRef, ActorRef appRootRef) {
    NodeMessageListener messageListener = _actor.getCollectResult().getMessageListener();
    return _actor.context().actorOf(NodeReceiveAktor.props(messageListener, sendRef, appRootRef));
  }

  private final NodeStartActor _actor;
}
