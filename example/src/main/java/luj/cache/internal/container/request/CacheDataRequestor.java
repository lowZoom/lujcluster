package luj.cache.internal.container.request;

import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.tree.RequestNodeState;

public interface CacheDataRequestor {

  interface Factory {

    static CacheDataRequestor create(CacheContainerState containerState,
        CacheContainerImpl containerImpl, RequestNodeState reqRootNode, Object reqParam) {
      return new CacheDataRequestorImpl(containerState, containerImpl, reqRootNode, reqParam);
    }
  }

  void request();
}
