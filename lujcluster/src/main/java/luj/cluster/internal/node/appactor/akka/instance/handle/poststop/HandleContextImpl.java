package luj.cluster.internal.node.appactor.akka.instance.handle.poststop;

import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

final class HandleContextImpl implements ActorPostStopHandler.Context {

  HandleContextImpl(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <A> A getActorState(ActorPostStopHandler<A> handler) {
    return (A) _appAktor.getState();
  }

  private final AppAktor _appAktor;
}
