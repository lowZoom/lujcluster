package luj.cache.internal.container.request;

import static com.google.common.base.Preconditions.checkState;

import java.util.List;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.CacheContainerImpl;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.container.request.hit.HitEntryCollector;
import luj.cache.internal.container.request.miss.collect.MissingKeyCollector;
import luj.cache.internal.container.request.miss.event.RequestMissFirer;
import luj.cache.internal.request.tree.RequestNodeState;

final class CacheDataRequestorImpl implements CacheDataRequestor {

  CacheDataRequestorImpl(CacheContainerState containerState,
      CacheContainerImpl containerImpl, RequestNodeState requestRootNode, Object reqParam) {
    _containerState = containerState;
    _containerImpl = containerImpl;

    _requestRootNode = requestRootNode;
    _reqParam = reqParam;
  }

  @Override
  public void request() {
    List<CacheKey> missList = MissingKeyCollector.Factory
        .create(_containerImpl, _requestRootNode).collect();

    if (!missList.isEmpty()) {
      RequestMissFirer.Factory.create(_containerState, _reqParam, missList).fire();
      return;
    }

    //TODO: 看是否上锁
    List<CacheEntry> hitList = HitEntryCollector.Factory.create().collect();

    for (CacheEntry entry : hitList) {
      //TODO: 只锁定要传出到请求结果的数据

      checkState(!entry.isLock());
      entry.setLock(true);
    }

    _containerState.getBeanCollect().getRequestReadyListener().onReady(null);
  }

  private final CacheContainerState _containerState;
  private final CacheContainerImpl _containerImpl;

  private final RequestNodeState _requestRootNode;
  private final Object _reqParam;
}
