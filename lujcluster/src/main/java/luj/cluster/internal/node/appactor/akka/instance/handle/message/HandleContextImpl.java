package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

@SuppressWarnings("unchecked")
final class HandleContextImpl implements ActorMessageHandler.Context {

  HandleContextImpl(AppAktor appAktor, Object msg) {
    _appAktor = appAktor;
    _msg = msg;
  }

  @Override
  public <A> A getActor(ActorMessageHandler<A, ?> handler) {
    return (A) _appAktor.getState();
  }

  @Override
  public <M> M getMessage(ActorMessageHandler<?, M> handler) {
    return (M) _msg;
  }

  private final AppAktor _appAktor;

  private final Object _msg;
}
