package luj.cache.api.request;

import luj.cache.internal.request.tree.RequestNodeState;

public interface CacheRequest {

  void addNode(Class<?> dataType, Object dataId);

  RequestNodeState getRootNode();
}
