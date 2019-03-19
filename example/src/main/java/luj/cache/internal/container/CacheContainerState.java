package luj.cache.internal.container;

import luj.cache.internal.session.inject.CacheBeanCollector;

public class CacheContainerState {

  public CacheContainerState(CacheBeanCollector.Result beanCollect) {
    _beanCollect = beanCollect;
  }

  public CacheBeanCollector.Result getBeanCollect() {
    return _beanCollect;
  }

  private final CacheBeanCollector.Result _beanCollect;
}
