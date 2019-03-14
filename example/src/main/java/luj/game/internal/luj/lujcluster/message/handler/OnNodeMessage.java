package luj.game.internal.luj.lujcluster.message.handler;

import luj.ava.spring.Internal;
import luj.cluster.api.message.NodeMessageListener;
import luj.game.api.proto.GameProtoHandler;

@Internal
final class OnNodeMessage implements NodeMessageListener {

  @Override
  public void onMessage(Context ctx) {
    Message message = ctx.getMessage();

    GameProtoHandler<Object> handler = message.getHandler();

    //TODO: 要能拿到dataActor

//    JamverInLujcluster appBean = ctx.getApplicationBean();

    HandleContextImpl handleCtx = new HandleContextImpl(message.getPayload(), ctx);

    handler.onHandle(handleCtx);
  }
}
