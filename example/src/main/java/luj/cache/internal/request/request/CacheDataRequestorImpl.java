package luj.cache.internal.request.request;

import java.util.Collection;
import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.CacheRequestImpl;
import luj.cache.internal.request.request.hit.HitEntryCollector;
import luj.cache.internal.request.request.miss.collect.MissingKeyCollector;
import luj.cache.internal.request.request.miss.event.RequestMissFirer;
import luj.cache.internal.request.request.result.RequestSuccessFinisher;

final class CacheDataRequestorImpl implements CacheDataRequestor {

   CacheDataRequestorImpl(CacheContainerState containerState,
      CacheContainer containerImpl, CacheRequestImpl request, Object reqParam) {
    _containerState = containerState;
    _containerImpl = containerImpl;
    _request = request;
    _reqParam = reqParam;
  }

  @Override
  public boolean tryRequest() {
    List<CacheKey> missList = MissingKeyCollector.Factory
        .create(_containerImpl, _request.getRootNode()).collect();

    if (!missList.isEmpty()) {
      RequestMissFirer.Factory.create(_containerState, _containerImpl, _reqParam, missList).fire();
      return false;
    }

    Collection<CacheEntry> hitList = HitEntryCollector.Factory
        .create(_request.getRootNode(), _containerImpl).collect();
    //TODO: 看是否上锁

    Requesting requesting = new Requesting(_containerImpl, _containerState, _reqParam);
    RequestSuccessFinisher.Factory.create(_request, requesting).finish();
    return true;
  }

  private final CacheContainerState _containerState;
  private final CacheContainer _containerImpl;

  private final CacheRequestImpl _request;
  private final Object _reqParam;
}
