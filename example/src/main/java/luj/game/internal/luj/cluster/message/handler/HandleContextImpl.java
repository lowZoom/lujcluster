package luj.game.internal.luj.cluster.message.handler;

import luj.cluster.api.message.NodeMessageListener;
import luj.game.api.proto.GameProtoHandler;

final class HandleContextImpl implements GameProtoHandler.Context<Object> {

  HandleContextImpl(Object proto, NodeMessageListener.Context listenCtx) {
    _proto = proto;
    _listenCtx = listenCtx;
  }

  @Override
  public Object proto() {
    return _proto;
  }

  @Override
  public void sendProto(Object proto) {
    _listenCtx.sendMessage(proto.getClass().getName(), proto);
  }

  @Override
  public void fireEvent(Object event) {
    sendProto(event);
  }

  private final Object _proto;

  private final NodeMessageListener.Context _listenCtx;
}
