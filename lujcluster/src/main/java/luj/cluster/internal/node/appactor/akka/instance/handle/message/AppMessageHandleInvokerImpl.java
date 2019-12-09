package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import static com.google.common.base.Preconditions.checkNotNull;

import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.logging.LogFactory;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class AppMessageHandleInvokerImpl implements AppMessageHandleInvoker {

  AppMessageHandleInvokerImpl(AppAktor appAktor, Object msg, ActorMessageHandler.Node remoteNode) {
    _appAktor = appAktor;
    _msg = msg;
    _remoteNode = remoteNode;
  }

  @Override
  public void invoke() {
    Class<?> msgType = _msg.getClass();
    ActorMessageHandleMap handleMap = _appAktor.getMeta().getMessageHandleMap();

    ActorMessageHandler<?, ?> handler = handleMap.getHandler(msgType);
    checkNotNull(handler, "%s @ %s", msgType.getName(), _appAktor.getState().getClass().getName());

    //FIXME: temp
    Log log = LogFactory.get(_appAktor, handler);

    HandleContextImpl ctx = new HandleContextImpl(_appAktor, log, _msg, _remoteNode);
    try {
      handler.onHandle(ctx);
    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final AppAktor _appAktor;

  private final Object _msg;

  private final ActorMessageHandler.Node _remoteNode;
}
