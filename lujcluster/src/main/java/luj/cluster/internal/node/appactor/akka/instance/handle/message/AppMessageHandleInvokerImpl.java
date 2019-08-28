package luj.cluster.internal.node.appactor.akka.instance.handle.message;

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

    //FIXME: temp
    Log log = LogFactory.get(_appAktor, handler);

    HandleContextImpl ctx = new HandleContextImpl(_appAktor, log, _msg, _remoteNode);
    handler.onHandle(ctx);
  }

  private final AppAktor _appAktor;

  private final Object _msg;

  private final ActorMessageHandler.Node _remoteNode;
}
