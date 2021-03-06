package luj.cache.internal.container.request;

import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.CacheRequestImpl;
import luj.cache.internal.request.tree.RequestNodeState;

public interface CacheDataRequestOrWaiter {

  interface Factory {

    static CacheDataRequestOrWaiter create(CacheContainerState containerState,
        CacheContainer containerFacade, RequestNodeState reqRootNode, CacheRequestImpl request, Object reqParam) {
      return new CacheDataRequestOrWaiterImpl(new RequestImpl(
          reqRootNode, request, reqParam, containerState, containerFacade));
    }
  }

  void requestOrWait();
}
