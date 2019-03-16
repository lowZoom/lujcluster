package luj.cache.api.container;

import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;

public interface CacheContainer<K> {

  CacheEntry getEntry(K key);

  boolean contains(K key);

  void request(CacheRequest req);

  CacheRequeue getRequestQueue();
}
