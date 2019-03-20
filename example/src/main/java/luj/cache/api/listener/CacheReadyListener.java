package luj.cache.api.listener;

import luj.cache.api.request.CacheRequest;

public interface CacheReadyListener {

  interface Context {

    CacheRequest getRequest();

    <T> T getRequestParam();

    ResultBuilder getResultBuilder();
  }

  interface ResultBuilder {

    Object build();
  }

  void onReady(Context ctx);
}
