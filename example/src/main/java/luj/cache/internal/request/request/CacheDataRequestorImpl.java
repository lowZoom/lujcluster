package luj.cache.internal.request.request;

import static com.google.common.base.Preconditions.checkState;

import java.util.Collection;
import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.request.hit.HitEntryCollector;
import luj.cache.internal.request.request.miss.collect.MissingKeyCollector;
import luj.cache.internal.request.request.miss.event.RequestMissFirer;
import luj.cache.internal.request.tree.RequestNodeState;

final class CacheDataRequestorImpl implements CacheDataRequestor {

  CacheDataRequestorImpl(CacheContainerState containerState,
      CacheContainer containerImpl, RequestNodeState requestRootNode, Object reqParam) {
    _containerState = containerState;
    _containerImpl = containerImpl;

    _requestRootNode = requestRootNode;
    _reqParam = reqParam;
  }

  @Override
  public boolean tryRequest() {
    List<CacheKey> missList = MissingKeyCollector.Factory
        .create(_containerImpl, _requestRootNode).collect();

    if (!missList.isEmpty()) {
      RequestMissFirer.Factory.create(_containerState, _containerImpl, _reqParam, missList).fire();
      return false;
    }

    Collection<CacheEntry> hitList = HitEntryCollector.Factory
        .create(_requestRootNode, _containerImpl).collect();
    //TODO: 看是否上锁

    //TODO:

    for (CacheEntry entry : hitList) {
      //TODO: 只锁定要传出到请求结果的数据

      checkState(!entry.isLock());
      entry.setLock(true);
    }

    fireReady();
    return true;
  }

  private void fireReady() {
    ReadyContextImpl ctx = new ReadyContextImpl(_reqParam, resultBuilder);
    _containerState.getBeanCollect().getRequestReadyListener().onReady(ctx);
  }

  private final CacheContainerState _containerState;
  private final CacheContainer _containerImpl;

  private final RequestNodeState _requestRootNode;
  private final Object _reqParam;
}
