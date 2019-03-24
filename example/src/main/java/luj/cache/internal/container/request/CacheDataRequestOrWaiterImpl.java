package luj.cache.internal.container.request;

final class CacheDataRequestOrWaiterImpl implements CacheDataRequestOrWaiter {

  CacheDataRequestOrWaiterImpl(Request req) {
    _req = req;
  }

  @Override
  public void requestOrWait() {
    if (_req.tryGetCache()) {
      return;
    }
    _req.addToWaitQueue();
  }

  interface Request {

    boolean tryGetCache();

    void addToWaitQueue();
  }

  private final Request _req;
}
