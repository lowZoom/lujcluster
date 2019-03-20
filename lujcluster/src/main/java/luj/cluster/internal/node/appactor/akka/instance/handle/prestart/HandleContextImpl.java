package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.api.actor.ActorPrestartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

@SuppressWarnings("unchecked")
final class HandleContextImpl implements ActorPrestartHandler.Context {

  HandleContextImpl(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @Override
  public <A> A getActor(ActorPrestartHandler<A> handler) {
    return (A) _appAktor.getState();
  }

  @Override
  public ActorPrestartHandler.Actor createActor(Object actorState) {
    return new ActorImpl(_appAktor.self());
  }

  private final AppAktor _appAktor;
}
