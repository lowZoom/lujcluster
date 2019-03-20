package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.api.actor.ActorPrestartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

final class AppPrestartHandleInvokerImpl implements AppPrestartHandleInvoker {

  @Override
  public void invoke() {
    ActorPrestartHandler<?> handler = _appAktor.getMeta().getPrestartHandler();
    handler.onHandle();
  }

  private final AppAktor _appAktor;
}
