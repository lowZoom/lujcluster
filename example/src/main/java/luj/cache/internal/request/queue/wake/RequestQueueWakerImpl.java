package luj.cache.internal.request.queue.wake;

final class RequestQueueWakerImpl implements RequestQueueWaker {

  RequestQueueWakerImpl(ReqQueue queue) {
    _queue = queue;
  }

  @Override
  public void wake() {
    Elem iter = _queue.iter();

    while (!iter.isEnd()) {
      if (iter.tryRequest()) {
        iter.remove();
      }
      iter = iter.next();
    }
  }

  interface ReqQueue {

    Elem iter();
  }

  interface Elem {

    boolean isEnd();

    Elem next();

    boolean tryRequest();

    void remove();
  }

  private final ReqQueue _queue;
}
