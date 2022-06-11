package luj.cluster.internal.node.shutdown;

import luj.cluster.api.node.NodeShutdownListener;

final class ShutdownContextImpl implements NodeShutdownListener.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getStartParam() {
    return (T) _startParam;
  }

  Object _startParam;
}
