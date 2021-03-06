package luj.game.internal.luj.lujcluster;

import luj.cluster.api.node.NodeStartListener;
import luj.game.api.start.GameStartListener;

final class StartContextImpl implements GameStartListener.Context {

  StartContextImpl(NodeStartListener.Context nodeStartCtx) {
    _nodeStartCtx = nodeStartCtx;
  }

  @Override
  public void sendServerMessage(Object msg) {
    _nodeStartCtx.sendMessage(msg.getClass().getName(), msg);
  }

  private final NodeStartListener.Context _nodeStartCtx;
}
