package luj.cache.internal.container.request.miss;

import java.util.List;
import java.util.function.Function;
import luj.cache.api.container.CacheEntry;
import luj.cache.internal.request.tree.RequestNodeState;

final class RequestNodeImpl implements MissingKeyCollectorImpl.RequestNode {

  RequestNodeImpl(RequestNodeState nodeState, CacheEntry parentNodeEntry) {
    _nodeState = nodeState;
    _parentNodeEntry = parentNodeEntry;
  }

  @Override
  public MissingKeyCollectorImpl.Entry getCacheEntry() {
    String parentId = null;

    Function<Object, Object> idGetter = _nodeState.getIdGetter();
    Object id = idGetter.apply(_parentNodeEntry.getData());

    return new EntryImpl(_nodeState.getCacheContainer(), null);
  }

  @Override
  public List<MissingKeyCollectorImpl.RequestNode> getChildren() {
    return null;
  }

  private final RequestNodeState _nodeState;

  private final CacheEntry _parentNodeEntry;
}
