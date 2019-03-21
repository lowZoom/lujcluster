package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;

@SuppressWarnings("unchecked")
final class HandleContextImpl implements ActorPreSstartHandler.Context {

  HandleContextImpl(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @Override
  public <A> A getActorState(ActorPreSstartHandler<A> handler) {
    return (A) _appAktor.getState();
  }

  @Override
  public ActorPreSstartHandler.Actor getActor() {
    return new ActorImpl(_appAktor.self());
  }

  @Override
  public ActorPreSstartHandler.Actor createActor(Object actorState) {
    ActorRef ref = AppAktorCreator.Factory.create(_appAktor.getActorMetaMap(),
        actorState.getClass(), actorState, _appAktor.context()).create();

    return new ActorImpl(ref);
  }

  private final AppAktor _appAktor;
}
