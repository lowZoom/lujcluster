package luj.cache.internal.request.request.miss.event;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;
import luj.cache.api.listener.CacheMissListener;

final class MissContextImpl implements CacheMissListener.Context {

   MissContextImpl(CacheKey<?> missingKey, Object requestParam,
      CacheContainer cacheContainer) {
    _missingKey = missingKey;
    _requestParam = requestParam;
    _cacheContainer = cacheContainer;
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

  @Override
  public CacheContainer getCacheContainer() {
    return _cacheContainer;
  }

  private final CacheKey<?> _missingKey;
  private final Object _requestParam;

  private final CacheContainer _cacheContainer;
}
