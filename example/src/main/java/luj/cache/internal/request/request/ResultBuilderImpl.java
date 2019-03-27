package luj.cache.internal.request.request;

import luj.cache.api.listener.CacheReadyListener.ResultBuilder;
import luj.cache.internal.request.tree.RequestNodeState;

final class ResultBuilderImpl implements ResultBuilder {

  ResultBuilderImpl(RequestNodeState rootNode) {
    _rootNode = rootNode;
  }

  @Override
  public Object build() {
    return _rootNode.ge;
  }

  private final RequestNodeState _rootNode;
}
