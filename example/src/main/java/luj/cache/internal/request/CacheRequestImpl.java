package luj.cache.internal.request;

import luj.cache.api.request.CacheRequest;
import luj.cache.internal.request.tree.RequestNodeState;

@Deprecated
public class CacheRequestImpl implements CacheRequest {

  @Override
  public void addNode(Class<?> dataType, Object dataId) {
    _reqNode = new RequestNodeState(dataType, o -> dataId);
  }

  @Override
  public RequestNodeState getRootNode() {
    return _reqNode;
  }

  private RequestNodeState _reqNode;
}
