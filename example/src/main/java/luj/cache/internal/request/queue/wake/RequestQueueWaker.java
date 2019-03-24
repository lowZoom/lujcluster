package luj.cache.internal.request.queue.wake;

import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;

public interface RequestQueueWaker {

  interface Factory {

    static RequestQueueWaker create(CacheContainerState containerState, CacheContainerImpl containerImpl) {
      return new RequestQueueWakerImpl(new ReqQueueImpl(containerState, containerImpl));
    }
  }

  void wake();
}
