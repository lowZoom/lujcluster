package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public class AppPrestartHandleInvoker {

  public AppPrestartHandleInvoker(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  public void invoke() throws Exception {
    ActorPreStartHandler<?> handler = _appAktor.getMeta().getPreStartHandler();
    if (handler == null) {
      return;
    }

    HandleContextImpl ctx = new HandleContextImpl(_appAktor);
    handler.onHandle(ctx);
  }

  private final AppAktor _appAktor;
}
