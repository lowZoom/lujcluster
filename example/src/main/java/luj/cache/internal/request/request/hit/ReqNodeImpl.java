package luj.cache.internal.request.request.hit;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.key.CacheKeyImpl;
import luj.cache.internal.request.request.hit.HitEntryCollectorImpl.ReqNode;
import luj.cache.internal.request.tree.RequestNodeState;

final class ReqNodeImpl implements ReqNode {

  ReqNodeImpl(RequestNodeState nodeState, CacheKey key,
      CacheContainer containerFacade) {
    _nodeState = nodeState;
    _key = key;
    _containerFacade = containerFacade;
  }

  @Override
  public CacheKey getKey() {
    Object dataId = _nodeState.getIdGetter().apply(null);
    return new CacheKeyImpl(_nodeState.getDataType(), dataId);
  }

  @Override
  public CacheEntry getEntry() {
    return _containerFacade.getEntry(_key);
  }

  @Override
  public List<ReqNode> getChildren() {
    return ImmutableList.of();
  }

  private final RequestNodeState _nodeState;
  private final CacheKey _key;

  private final CacheContainer _containerFacade;
}
