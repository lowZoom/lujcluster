package luj.cache.internal.container;

import java.util.List;
import java.util.Map;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.request.queue.RequestQueueElem;
import luj.cache.internal.session.inject.CacheBeanCollector;

public class CacheContainerState {

  public CacheContainerState(
      Map<CacheKey<?>, CacheEntry> cacheImpl,
      CacheBeanCollector.Result beanCollect, Object cacheParam,
      List<RequestQueueElem> reqQueue) {
    _cacheImpl = cacheImpl;
    _beanCollect = beanCollect;
    _cacheParam = cacheParam;
    _reqQueue = reqQueue;
  }

  public Map<CacheKey<?>, CacheEntry> getCacheImpl() {
    return _cacheImpl;
  }

  public CacheBeanCollector.Result getBeanCollect() {
    return _beanCollect;
  }

  public Object getCacheParam() {
    return _cacheParam;
  }

  public List<RequestQueueElem> getReqQueue() {
    return _reqQueue;
  }

  private final Map<CacheKey<?>, CacheEntry> _cacheImpl;

  private final CacheBeanCollector.Result _beanCollect;
  private final Object _cacheParam;

  private final List<RequestQueueElem> _reqQueue;
}
