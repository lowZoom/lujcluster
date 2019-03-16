package luj.cache.internal.container;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.api.request.CacheRequest;
import luj.cache.api.request.CacheRequeue;
import luj.cache.internal.container.request.CacheDataRequestor;
import org.omg.CORBA.NO_IMPLEMENT;

final class CacheContainerImpl<K> implements CacheContainer<K> {

  CacheContainerImpl(CacheContainerState containerState) {
    _containerState = containerState;
  }

  @Override
  public void request(CacheRequest req) {
    CacheDataRequestor.Factory.create(null).request();
  }

  @Override
  public CacheEntry getEntry(CacheKey<K> key) {
    throw new NO_IMPLEMENT("getEntry尚未实现");
  }

  @Override
  public CacheRequeue getRequestQueue() {
    return null;
  }

  private final CacheContainerState _containerState;
}
