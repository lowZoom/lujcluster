package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorPreSstartHandler;

final class ActorImpl implements ActorPreSstartHandler.Actor {

  ActorImpl(ActorRef aktorRef) {
    _aktorRef = aktorRef;
  }

  @Override
  public void tell(Object msg) {
    _aktorRef.tell(msg, ActorRef.noSender());
  }

  private final ActorRef _aktorRef;
}
