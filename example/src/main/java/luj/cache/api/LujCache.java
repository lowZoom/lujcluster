package luj.cache.api;

import java.util.HashMap;
import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.session.inject.CacheBeanCollector;
import org.springframework.context.ApplicationContext;

public enum LujCache {
  ;

  public static <T> CacheContainer<T> createCache(ApplicationContext appContext) {
    CacheBeanCollector.Result collect = CacheBeanCollector.Factory.create(appContext).collect();
    return new CacheContainerImpl<>(new CacheContainerState(collect, new HashMap<>()));
  }
}
