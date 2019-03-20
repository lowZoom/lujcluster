package luj.cache.internal.container;

import java.util.Map;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.session.inject.CacheBeanCollector;

public class CacheContainerState {

  public CacheContainerState(CacheBeanCollector.Result beanCollect,
      Map<CacheKey<?>, CacheEntry> cacheImpl) {
    _beanCollect = beanCollect;
    _cacheImpl = cacheImpl;
  }

  public CacheBeanCollector.Result getBeanCollect() {
    return _beanCollect;
  }

  public Map<CacheKey<?>, CacheEntry> getCacheImpl() {
    return _cacheImpl;
  }

  private final CacheBeanCollector.Result _beanCollect;

  private final Map<CacheKey<?>, CacheEntry> _cacheImpl;
}
