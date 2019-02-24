package luj.cluster.internal.node.start.actor;

import akka.actor.ActorRef;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.api.start.NodeStartListener;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveActor;
import luj.cluster.internal.node.message.send.actor.NodeSendActor;

final class PreStart {

  PreStart(NodeStartActor actor) {
    _actor = actor;
  }

  void run() {
    System.out.print("[");
    System.out.print(Thread.currentThread().getName());
    System.out.println("] akka启动");

    ActorRef sendRef = createSendActor();
    ActorRef receiveRef = createReceiveActor(sendRef);

    ContextImpl context = new ContextImpl(receiveRef, sendRef, null);
    for (NodeStartListener listener : _actor.getCollectResult().getStartListeners()) {
      listener.onStart(context);
    }
  }

  private ActorRef createSendActor() {
    return _actor.context().actorOf(NodeSendActor.props());
  }

  private ActorRef createReceiveActor(ActorRef sendRef) {
    NodeMessageListener messageListener = _actor.getCollectResult().getMessageListener();
    return _actor.context().actorOf(NodeReceiveActor.props(messageListener, sendRef));
  }

  private final NodeStartActor _actor;
}
