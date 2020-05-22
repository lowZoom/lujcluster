package luj.cluster.internal.node.appactor.akka.instance.handle.poststop;

import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public class AppPoststopHandleInvoker {

  public AppPoststopHandleInvoker(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  public void invoke() throws Exception {
    ActorPostStopHandler<?> handler = _appAktor.getMeta().getPostStopHandler();
    if (handler == null) {
      return;
    }

    HandleContextImpl ctx = new HandleContextImpl(_appAktor);
    handler.onHandle(ctx);
  }

  private final AppAktor _appAktor;
}
