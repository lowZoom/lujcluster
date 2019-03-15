package luj.cache.api.container;

import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;

public interface CacheContainer<K> {

  void request(CacheRequest req);

  CacheEntry getEntry(K key);

  CacheRequeue getRequestQueue();
}
