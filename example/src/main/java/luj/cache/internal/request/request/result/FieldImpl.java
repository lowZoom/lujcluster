package luj.cache.internal.request.request.result;

import static com.google.common.base.Preconditions.checkState;

import luj.cache.api.container.CacheEntry;
import luj.cache.internal.request.tree.RequestNodeState;

final class FieldImpl implements RequestSuccessFinisherImpl.Field {

  FieldImpl(RequestNodeState nodeState, Object result, CacheEntry cacheEntry) {
    _nodeState = nodeState;
    _result = result;
    _cacheEntry = cacheEntry;
  }

  @Override
  public void fill() {
    checkState(!_cacheEntry.isLock());
    _cacheEntry.setLock(true);

    Object data = _cacheEntry.getData();
    _nodeState.getResultFieldSetter().accept(_result, data);
  }

  private final RequestNodeState _nodeState;

  private final Object _result;
  private final CacheEntry _cacheEntry;
}
