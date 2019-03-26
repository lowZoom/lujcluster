package luj.cache.api.container;

import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;

public interface CacheContainer<T> {

  CacheEntry getEntry(CacheKey<T> key);

  CacheEntry createEntry(CacheKey<T> key);

  void request(CacheRequest req, Object param);

  CacheRequeue getRequestQueue();
}
