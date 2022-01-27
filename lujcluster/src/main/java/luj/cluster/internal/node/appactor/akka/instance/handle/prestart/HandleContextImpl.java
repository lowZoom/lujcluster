package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;

final class HandleContextImpl implements ActorPreStartHandler.Context {

  HandleContextImpl(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <A> A getActorState(ActorPreStartHandler<A> handler) {
    return (A) _appAktor.getState();
  }

  @Override
  public ActorPreStartHandler.Actor getActor() {
    ActorImpl actor = new ActorImpl();
    actor._aktorRef = _appAktor.self();
    return actor;
  }

  @Override
  public ActorPreStartHandler.Actor createActor(Object actorState) {
    ActorImpl actor = new ActorImpl();

    actor._aktorRef = new AppAktorCreator(_appAktor.getActorMetaMap(), actorState.getClass(),
        actorState, _appAktor.getClusterMemberRef(), _appAktor.context()).create();

    return actor;
  }

  private final AppAktor _appAktor;
}
