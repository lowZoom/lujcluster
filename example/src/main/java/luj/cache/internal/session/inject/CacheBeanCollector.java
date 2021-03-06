package luj.cache.internal.session.inject;

import luj.cache.api.listener.CacheMissListener;
import luj.cache.api.listener.CacheReadyListener;
import luj.cache.api.listener.RequestResultFactory;
import org.springframework.context.ApplicationContext;

public interface CacheBeanCollector {

  interface Factory {

    static CacheBeanCollector create(ApplicationContext appContext) {
      return new CacheBeanCollectorImpl(appContext);
    }
  }

  interface Result {

    CacheReadyListener getRequestReadyListener();

    CacheMissListener getRequestEntryMissListener();

    RequestResultFactory getRequestResultFactory();
  }

  Result collect();
}
