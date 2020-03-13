package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.time.Duration;
import luj.cluster.api.actor.ActorMessageHandler;

final class ActorRefImpl implements ActorMessageHandler.Ref {

  ActorRefImpl(ActorRef actorRef, ActorContext actorContext) {
    _actorRef = actorRef;
    _actorContext = actorContext;
  }

  @Override
  public void tell(Object msg, Duration delay) {
    ActorRef sender = _actorContext.sender();
    if (delay.equals(Duration.ZERO)) {
      _actorRef.tell(msg, sender);
      return;
    }

    // schedule零秒不能保证消息先发先到！
    ActorSystem system = _actorContext.system();
    system.scheduler().scheduleOnce(delay, _actorRef, msg, system.dispatcher(), sender);
  }

  private final ActorRef _actorRef;

  private final ActorContext _actorContext;
}
