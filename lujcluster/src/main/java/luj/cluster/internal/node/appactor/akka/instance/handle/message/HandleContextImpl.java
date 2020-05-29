package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;

final class HandleContextImpl implements ActorMessageHandler.Context {

  HandleContextImpl(AppAktor appAktor, Log logger, Object msg,
      ActorMessageHandler.Node remoteNode) {
    _appAktor = appAktor;
    _logger = logger;
    _msg = msg;
    _remoteNode = remoteNode;
  }

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
  public ActorMessageHandler.Ref getSenderRef() {
    return new ActorRefImpl(_appAktor.sender(), _appAktor.context(), _appAktor.self());
  }

  @Override
  public ActorRef createActor(Object actorState) {
    return AppAktorCreator.Factory.create(_appAktor.getActorMetaMap(), actorState.getClass(),
        actorState, _appAktor.context()).create();
  }

  private final AppAktor _appAktor;
  private final Object _msg;

  private final Log _logger;
  private final ActorMessageHandler.Node _remoteNode;
}
