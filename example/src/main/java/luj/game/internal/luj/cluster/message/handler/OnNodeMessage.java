package luj.game.internal.luj.cluster.message.handler;

import luj.ava.spring.Internal;
import luj.cluster.api.message.NodeMessageListener;
import luj.game.api.proto.GameProtoHandler;

@Internal
final class OnNodeMessage implements NodeMessageListener {

  @Override
  public void onMessage(Context ctx) {
    Message message = ctx.getMessage();

    GameProtoHandler<Object> handler = (GameProtoHandler<Object>) message.getHandler();
    HandleContextImpl context = new HandleContextImpl(message.getPayload());

    handler.onHandle(context);
  }
}
