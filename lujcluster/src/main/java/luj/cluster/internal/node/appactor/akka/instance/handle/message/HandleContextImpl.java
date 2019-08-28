package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

@SuppressWarnings("unchecked")
final class HandleContextImpl implements ActorMessageHandler.Context {

  HandleContextImpl(AppAktor appAktor, Log logger, Object msg,
      ActorMessageHandler.Node remoteNode) {
    _appAktor = appAktor;
    _logger = logger;

    _msg = msg;
    _remoteNode = remoteNode;
  }

  @Override
  public <A> A getActor(ActorMessageHandler<A, ?> handler) {
    return (A) _appAktor.getState();
  }

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

  private final AppAktor _appAktor;
  private final Log _logger;

  private final Object _msg;

  private final ActorMessageHandler.Node _remoteNode;
}
