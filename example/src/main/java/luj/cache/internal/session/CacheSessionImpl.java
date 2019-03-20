package luj.cache.internal.session;

import java.util.HashMap;
import luj.cache.api.CacheSession;
import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.session.inject.CacheBeanCollector;
import org.springframework.context.ApplicationContext;

final class CacheSessionImpl implements CacheSession {

  CacheSessionImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public <T> CacheContainer<T> createCache(Object cacheParam) {
    CacheBeanCollector.Result collect = CacheBeanCollector.Factory.create(_appContext).collect();
    return new CacheContainerImpl<>(new CacheContainerState(new HashMap<>(), collect, cacheParam));
  }

  private final ApplicationContext _appContext;
}
