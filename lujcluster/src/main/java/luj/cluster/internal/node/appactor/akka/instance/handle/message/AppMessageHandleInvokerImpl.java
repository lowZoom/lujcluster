package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class AppMessageHandleInvokerImpl implements AppMessageHandleInvoker {

  AppMessageHandleInvokerImpl(AppAktor appAktor, Object msg) {
    _appAktor = appAktor;
    _msg = msg;
  }

  @Override
  public void invoke() {
    Class<?> msgType = _msg.getClass();
    ActorMessageHandleMap handleMap = _appAktor.getMeta().getMessageHandleMap();
    ActorMessageHandler<?, ?> handler = handleMap.getHandler(msgType);

    HandleContextImpl ctx = new HandleContextImpl(_appAktor, _msg);
    handler.onHandle(ctx);
  }

  private final AppAktor _appAktor;

  private final Object _msg;
}
