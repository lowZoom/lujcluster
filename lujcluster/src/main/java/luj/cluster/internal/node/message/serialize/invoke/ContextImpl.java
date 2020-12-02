package luj.cluster.internal.node.message.serialize.invoke;

import luj.cluster.api.node.NodeMessageSerializer;

final class ContextImpl implements NodeMessageSerializer.Context {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationBean() {
    return (T) _appBean;
  }

  Object _appBean;
}
