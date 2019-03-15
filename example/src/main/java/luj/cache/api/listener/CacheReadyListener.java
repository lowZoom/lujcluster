package luj.cache.api.listener;

import luj.cache.api.request.CacheRequest;

public interface CacheReadyListener {

  interface Context {

    CacheRequest getRequest();

    ResultBuilder getResultBuilder();
  }

  interface ResultBuilder {

    Object build();
  }

  void onReady(Context ctx);
}
