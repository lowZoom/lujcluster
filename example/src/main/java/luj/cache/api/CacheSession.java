package luj.cache.api;

import luj.cache.api.container.CacheContainer;

public interface CacheSession {

  <T> CacheContainer<T> createCache(Object cacheParam);
}
