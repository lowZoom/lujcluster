package luj.cache.api.request;

public interface CacheRequest {

  void addJoinReq();

  <T> T getParam();
}
