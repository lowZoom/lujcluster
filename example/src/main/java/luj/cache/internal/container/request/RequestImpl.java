package luj.cache.internal.container.request;

import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.queue.RequestQueueElem;
import luj.cache.internal.request.request.CacheDataRequestor;
import luj.cache.internal.request.tree.RequestNodeState;

final class RequestImpl implements CacheDataRequestOrWaiterImpl.Request {

  RequestImpl(RequestNodeState reqRootNode, Object reqParam,
      CacheContainerState containerState, CacheContainer containerFacade) {
    _reqRootNode = reqRootNode;
    _reqParam = reqParam;
    _containerState = containerState;
    _containerFacade = containerFacade;
  }

  @Override
  public boolean tryGetCache() {
    return CacheDataRequestor.Factory.create(_containerState,
        _containerFacade, _reqRootNode, _reqParam).tryRequest();
  }

  @Override
  public void addToWaitQueue() {
    RequestQueueElem elem = new RequestQueueElem(_reqRootNode, _reqParam);
    _containerState.getReqQueue().add(elem);
  }

  private final RequestNodeState _reqRootNode;
  private final Object _reqParam;

  private final CacheContainerState _containerState;
  private final CacheContainer _containerFacade;
}
