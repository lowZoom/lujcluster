package luj.cache.internal.container;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;

final class CacheContainerImpl<K> implements CacheContainer<K> {

  @Override
  public void request(CacheRequest req) {
    //TODO: 借数据，
  }

  @Override
  public CacheEntry getEntry(K key) {
    return null;
  }

  @Override
  public CacheRequeue getRequestQueue() {
    return null;
  }
}
