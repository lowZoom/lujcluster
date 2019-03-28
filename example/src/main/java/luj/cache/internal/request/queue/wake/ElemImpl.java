package luj.cache.internal.request.queue.wake;

import java.util.Iterator;
import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.queue.RequestQueueElem;
import luj.cache.internal.request.request.CacheDataRequestor;

final class ElemImpl implements RequestQueueWakerImpl.Elem {

   ElemImpl(RequestQueueElem elem,
      Iterator<RequestQueueElem> elemIter,
      CacheContainerState containerState,
      CacheContainerImpl containerImpl) {
    _elem = elem;
    _elemIter = elemIter;
    _containerState = containerState;
    _containerImpl = containerImpl;
  }

  @Override
  public boolean isEnd() {
    return _elem == null;
  }

  @Override
  public RequestQueueWakerImpl.Elem next() {
    return new ElemImpl(_elemIter.hasNext()?_elemIter.next():null, _elemIter, _containerState, _containerImpl);
  }

  @Override
  public boolean tryRequest() {
    return CacheDataRequestor.Factory.create(_containerState, _containerImpl,
        _elem.getRequest(), _elem.getReqParam()).tryRequest();
  }

  @Override
  public void remove() {
    _elemIter.remove();
  }

  private final RequestQueueElem _elem;
  private final Iterator<RequestQueueElem> _elemIter;

  private final CacheContainerState _containerState;
  private final CacheContainerImpl _containerImpl;
}
