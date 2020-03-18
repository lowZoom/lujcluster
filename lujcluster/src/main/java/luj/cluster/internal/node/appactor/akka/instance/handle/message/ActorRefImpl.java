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
  public void tell(Object msg) {
    _actorRef.tell(msg, _actorContext.sender());
  }

  @Override
  public void tell(Object msg, Duration delay) {
    if (delay.equals(Duration.ZERO)) {
      tell(msg);
      return;
    }

    // scheduler零秒不能保证消息先发先到！
    ActorSystem system = _actorContext.system();
    system.scheduler().scheduleOnce(delay, _actorRef,
        msg, system.dispatcher(), _actorContext.sender());
  }

  private final ActorRef _actorRef;

  private final ActorContext _actorContext;
}
