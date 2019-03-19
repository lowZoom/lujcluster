package luj.cache.internal.session.inject;

import luj.cache.api.listener.CacheReadyListener;
import org.springframework.context.ApplicationContext;

public interface CacheBeanCollector {

  interface Factory {

    static CacheBeanCollector create(ApplicationContext appContext) {
      return new CacheBeanCollectorImpl(appContext);
    }
  }

  interface Result {

    CacheReadyListener getRequestReadyListener();
  }

  Result collect();
}
