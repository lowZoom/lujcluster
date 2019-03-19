package luj.cache.internal.request;

import luj.cache.api.request.CacheRequest;
import luj.cache.internal.request.tree.RequestNodeState;

public class CacheRequestImpl implements CacheRequest {

  @Override
  public void add(Class<?> dataType, Object dataId) {
    _reqNode = new RequestNodeState(dataType, o->dataId, null);
  }

  @Override
  public <T> T getParam() {
    return null;
  }

  private RequestNodeState _reqNode;
}
