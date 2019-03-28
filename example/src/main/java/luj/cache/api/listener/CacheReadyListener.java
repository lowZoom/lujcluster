package luj.cache.api.listener;

public interface CacheReadyListener {

  interface Context {

    <T> T getRequestParam();

    Object getRequestResult();
  }

  void onReady(Context ctx);
}
