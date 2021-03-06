package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorRef;
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
    return new ActorImpl(_appAktor.self());
  }

  @Override
  public ActorPreStartHandler.Actor createActor(Object actorState) {
    ActorRef ref = new AppAktorCreator(_appAktor.getActorMetaMap(), actorState.getClass(),
        actorState, _appAktor.getClusterMemberRef(), _appAktor.context()).create();

    return new ActorImpl(ref);
  }

  private final AppAktor _appAktor;
}
