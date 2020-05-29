package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorPreStartHandler;

final class ActorImpl implements ActorPreStartHandler.Actor {

  ActorImpl(ActorRef aktorRef) {
    _aktorRef = aktorRef;
  }

  @Override
  public void tell(Object msg) {
    _aktorRef.tell(msg, ActorRef.noSender());
  }

  @Override
  public ActorRef getAkkaRef() {
    return _aktorRef;
  }

  private final ActorRef _aktorRef;
}
