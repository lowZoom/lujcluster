package luj.cache.api.listener;

public interface CacheReadyListener {

  interface Context {

    <T> T getRequestParam();

    ResultBuilder getResultBuilder();
  }

  interface ResultBuilder {

    Object build();
  }

  void onReady(Context ctx);
}
