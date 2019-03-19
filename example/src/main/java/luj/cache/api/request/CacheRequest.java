package luj.cache.api.request;

public interface CacheRequest {

  void add(Class<?> dataType, Object dataId);

  <T> T getParam();
}
