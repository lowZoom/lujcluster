package luj.cache.internal.request.request;

import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.CacheRequestImpl;

public interface CacheDataRequestor {

  interface Factory {

    static CacheDataRequestor create(CacheContainerState containerState,
        CacheContainer containerImpl, CacheRequestImpl request, Object reqParam) {
      return new CacheDataRequestorImpl(containerState, containerImpl, request, reqParam);
    }
  }

  boolean tryRequest();
}
