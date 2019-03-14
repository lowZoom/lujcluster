package luj.cache.api.container;

import luj.cache.api.request.CacheRequest;

public interface CacheContainer<K> {

  void addRequest(CacheRequest req);


}
