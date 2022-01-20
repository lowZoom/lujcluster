package luj.cluster.internal.node.appactor.akka.instance.handle.message.local;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;

final class HandleContextImpl implements ActorMessageHandler.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <A> A getActorState(ActorMessageHandler<A, ?> handler) {
    return (A) _appAktor.getState();
  }

  @Override
  public ActorMessageHandler.Ref getActorRef() {
    return new ActorRefImpl(_appAktor.self(), _appAktor.context(), _appAktor.sender());
  }

  @SuppressWarnings("unchecked")
  @Override
  public <M> M getMessage(ActorMessageHandler<?, M> handler) {
    return (M) _msg;
  }

  @Override
  public Log getLogger() {
    return _logger;
  }

  @Override
  public ActorMessageHandler.Node getRemoteNode() {
    return _remoteNode;
  }

  @Override
  public LocalNodeImpl getLocalNode() {
    return _localNode;
  }

  @Override
  public ActorMessageHandler.Ref getSenderRef() {
    return new ActorRefImpl(_appAktor.sender(), _appAktor.context(), _appAktor.self());
  }

  @Override
  public ActorRef createActor(Object actorState) {
    return new AppAktorCreator(_appAktor.getActorMetaMap(), actorState.getClass(),
        actorState, _appAktor.getClusterMemberRef(), _appAktor.context()).create();
  }

  AppAktor _appAktor;
  Object _msg;

  Log _logger;
  ActorMessageHandler.Node _remoteNode;

  LocalNodeImpl _localNode;
}
