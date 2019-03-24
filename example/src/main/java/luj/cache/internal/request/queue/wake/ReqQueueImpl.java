package luj.cache.internal.request.queue.wake;

import java.util.Iterator;
import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.queue.RequestQueueElem;

final class ReqQueueImpl implements RequestQueueWakerImpl.ReqQueue {

   ReqQueueImpl(CacheContainerState containerState, CacheContainerImpl containerImpl) {
    _containerState = containerState;
    _containerImpl = containerImpl;
  }

  @Override
  public RequestQueueWakerImpl.Elem iter() {
    Iterator<RequestQueueElem> queueIter = _containerState.getReqQueue().iterator();
    return new ElemImpl(queueIter.hasNext()?queueIter.next():null, queueIter, _containerState, _containerImpl);
  }

  private final CacheContainerState _containerState;

  private final CacheContainerImpl _containerImpl;
}
