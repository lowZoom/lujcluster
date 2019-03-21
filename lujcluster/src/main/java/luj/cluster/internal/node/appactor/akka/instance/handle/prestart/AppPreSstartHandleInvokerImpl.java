package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

final class AppPreSstartHandleInvokerImpl implements AppPreSstartHandleInvoker {

  AppPreSstartHandleInvokerImpl(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @Override
  public void invoke() {
    ActorPreSstartHandler<?> handler = _appAktor.getMeta().getPreStartHandler();
    if (handler == null) {
      return;
    }

    HandleContextImpl ctx = new HandleContextImpl(_appAktor);
    handler.onHandle(ctx);
  }

  private final AppAktor _appAktor;
}
