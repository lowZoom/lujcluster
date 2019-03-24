package luj.cache.internal.container.request;

public interface CacheDataRequestOrWaiter {

  interface Factory {

    static CacheDataRequestOrWaiter create() {
      return new CacheDataRequestOrWaiterImpl(new Reqimp);
    }
  }

  void requestOrWait();
}
