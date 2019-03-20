package luj.cache.internal.container.request.miss.event;

import luj.cache.api.container.CacheKey;
import luj.cache.api.listener.CacheMissListener;

final class MissContextImpl implements CacheMissListener.Context {

  MissContextImpl(CacheKey<?> missingKey, Object requestParam) {
    _missingKey = missingKey;
    _requestParam = requestParam;
  }

  @Override
  public CacheKey<?> getMissingKey() {
    return _missingKey;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getRequestParam() {
    return (T) _requestParam;
  }

  private final CacheKey<?> _missingKey;

  private final Object _requestParam;
}
