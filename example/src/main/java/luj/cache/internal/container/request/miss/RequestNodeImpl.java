package luj.cache.internal.container.request.miss;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Function;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.internal.request.tree.RequestNodeState;

final class RequestNodeImpl implements MissingKeyCollectorImpl.RequestNode {

  RequestNodeImpl(RequestNodeState nodeState,
      CacheContainer cacheContainer, CacheEntry parentNodeEntry) {
    _nodeState = nodeState;

    _cacheContainer = cacheContainer;
    _parentNodeEntry = parentNodeEntry;
  }

  @Override
  public MissingKeyCollectorImpl.Entry getCacheEntry() {
    String parentId = null;

    Function<Object, Object> idGetter = _nodeState.getIdGetter();
    Object id = idGetter.apply(null);//_parentNodeEntry.getData());

    return new EntryImpl(_cacheContainer, null);
  }

  @Override
  public List<MissingKeyCollectorImpl.RequestNode> getChildren() {
    return ImmutableList.of();
  }

  private final RequestNodeState _nodeState;

  private final CacheContainer _cacheContainer;
  private final CacheEntry _parentNodeEntry;
}
