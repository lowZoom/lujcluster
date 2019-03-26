package luj.cache.internal.container;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;
import luj.cache.internal.container.request.CacheDataRequestOrWaiter;
import luj.cache.internal.request.queue.wake.RequestQueueWaker;

public final class CacheContainerImpl<K> implements CacheContainer<K>, CacheRequeue {

  public CacheContainerImpl(CacheContainerState containerState) {
    _containerState = containerState;
  }

  @Override
  public void request(CacheRequest req, Object param) {
    CacheDataRequestOrWaiter.Factory.create(_containerState, this, req.getRootNode(), param).requestOrWait();
  }

  @Override
  public CacheEntry getEntry(CacheKey<K> key) {
    return _containerState.getCacheImpl().get(key);
  }

  @Override
  public CacheEntry createEntry(CacheKey<K> key) {
    CacheEntryImpl entry = new CacheEntryImpl();
    _containerState.getCacheImpl().put(key, entry);
    return entry;
  }

  @Override
  public CacheRequeue getRequestQueue() {
    return this;
  }

  @Override
  public void wake() {
    RequestQueueWaker.Factory.create(_containerState, this).wake();
  }

  private final CacheContainerState _containerState;
}
