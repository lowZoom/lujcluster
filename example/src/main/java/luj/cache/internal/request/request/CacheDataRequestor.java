package luj.cache.internal.request.request;

import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.tree.RequestNodeState;

public interface CacheDataRequestor {

  interface Factory {

    static CacheDataRequestor create(CacheContainerState containerState,
        CacheContainer containerImpl, RequestNodeState reqRootNode, Object reqParam) {
      return new CacheDataRequestorImpl(containerState, containerImpl, reqRootNode, reqParam);
    }
  }

  boolean tryRequest();
}
