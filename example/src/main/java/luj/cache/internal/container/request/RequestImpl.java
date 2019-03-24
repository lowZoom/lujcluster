package luj.cache.internal.container.request;

import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.queue.RequestQueueElem;
import luj.cache.internal.request.request.CacheDataRequestor;

final class RequestImpl implements CacheDataRequestOrWaiterImpl.Request {

  @Override
  public boolean tryGetCache() {
    return CacheDataRequestor.Factory.create().tryRequest();
  }

  @Override
  public void addToWaitQueue() {
    RequestQueueElem elem = new RequestQueueElem();
    _containerState.getReqQueue().add(elem);
  }

  private final CacheContainerState _containerState;
}
