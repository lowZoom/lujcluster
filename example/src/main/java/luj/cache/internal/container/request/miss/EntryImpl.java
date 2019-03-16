package luj.cache.internal.container.request.miss;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;

final class EntryImpl implements MissingEntryCollectorImpl.Entry {

  EntryImpl(CacheContainer container, CacheKey cacheKey) {
    _container = container;
    _cacheKey = cacheKey;
  }

  @Override
  public boolean isMissing() {
    return _container.getEntry(_cacheKey) == null;
  }

  @Override
  public Object getKey() {
    return _cacheKey;
  }

  private final CacheContainer _container;

  private final CacheKey _cacheKey;
}
