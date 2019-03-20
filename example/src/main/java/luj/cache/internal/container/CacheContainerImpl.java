package luj.cache.internal.container;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;
import luj.cache.internal.container.request.CacheDataRequestor;

public final class CacheContainerImpl<K> implements CacheContainer<K> {

  public CacheContainerImpl(CacheContainerState containerState) {
    _containerState = containerState;
  }

  @Override
  public void request(CacheRequest req) {
    CacheDataRequestor.Factory.create(_containerState, this, req.getRootNode()).request();
  }

  @Override
  public CacheEntry getEntry(CacheKey<K> key) {
    return _containerState.getCacheImpl().get(key);
  }

  @Override
  public CacheRequeue getRequestQueue() {
    return null;
  }

  private final CacheContainerState _containerState;
}
