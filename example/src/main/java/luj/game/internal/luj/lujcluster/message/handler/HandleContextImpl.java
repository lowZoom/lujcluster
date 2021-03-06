package luj.game.internal.luj.lujcluster.message.handler;

import luj.cluster.api.node.NodeMessageListener;
import luj.game.api.proto.GameProtoHandler;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorState;

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
  public GameProtoHandler.Player player() {
    return new HandlePlayerImpl(_listenCtx.getApplicationActor(DataActorState.class), null);
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
