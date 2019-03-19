package luj.cache.internal.session.inject;

import luj.ava.spring.Internal;
import luj.cache.api.listener.CacheReadyListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class CollectResultImpl implements CacheBeanCollector.Result {

  @Override
  public CacheReadyListener getRequestReadyListener() {
    return _requestReadyListener;
  }

  @Autowired
  private CacheReadyListener _requestReadyListener;
}
