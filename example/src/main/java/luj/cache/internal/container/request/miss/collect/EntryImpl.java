package luj.cache.internal.container.request.miss.collect;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;

final class EntryImpl implements MissingKeyCollectorImpl.Entry {

  EntryImpl(CacheContainer container, CacheKey cacheKey) {
    _container = container;
    _cacheKey = cacheKey;
  }

  @Override
  public boolean isMissing() {
    return _container.getEntry(_cacheKey) == null;
  }

  @Override
  public CacheKey getKey() {
    return _cacheKey;
  }

  private final CacheContainer _container;

  private final CacheKey _cacheKey;
}
