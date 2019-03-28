package luj.cache.internal.session.inject;

import luj.ava.spring.Internal;
import luj.cache.api.listener.CacheMissListener;
import luj.cache.api.listener.CacheReadyListener;
import luj.cache.api.listener.RequestResultFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class CollectResultImpl implements CacheBeanCollector.Result {

  @Override
  public CacheReadyListener getRequestReadyListener() {
    return _requestReadyListener;
  }

  @Override
  public CacheMissListener getRequestEntryMissListener() {
    return _reqEntryMissListener;
  }

  @Override
  public RequestResultFactory getRequestResultFactory() {
    return _requestResultFactory;
  }

  @Autowired
  private CacheReadyListener _requestReadyListener;

  @Autowired
  private CacheMissListener _reqEntryMissListener;

  @Autowired
  private RequestResultFactory _requestResultFactory;
}
