package luj.cache.internal.request.request.miss.event;

import java.util.List;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.CacheContainerState;

final class RequestMissFirerImpl implements RequestMissFirer {

  RequestMissFirerImpl(List<CacheKey> missKeyList,
      CacheContainerState containerState, Object reqParam) {
    _missKeyList = missKeyList;

    _containerState = containerState;
    _reqParam = reqParam;
  }

  @Override
  public void fire() {
    for (CacheKey key : _missKeyList) {
      triggerListener(key);
    }
  }

  private void triggerListener(CacheKey key) {
    MissContextImpl ctx = new MissContextImpl(key, _reqParam);
    _containerState.getBeanCollect().getRequestEntryMissListener().onMiss(ctx);
  }

  private final List<CacheKey> _missKeyList;

  private final CacheContainerState _containerState;
  private final Object _reqParam;
}
