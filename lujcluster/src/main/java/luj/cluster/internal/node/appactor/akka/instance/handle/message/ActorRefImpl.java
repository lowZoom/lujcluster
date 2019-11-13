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
    ActorSystem system = _actorContext.system();
    system.scheduler().scheduleOnce(delay,
        _actorRef, msg, system.dispatcher(), ActorRef.noSender());
  }

  private final ActorRef _actorRef;

  private final ActorContext _actorContext;
}
