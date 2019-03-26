package luj.cache.internal.request.request.hit;

import java.util.Collection;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.internal.container.key.CacheKeyImpl;
import luj.cache.internal.request.tree.RequestNodeState;

public interface HitEntryCollector {

  interface Factory {

    static HitEntryCollector create(RequestNodeState rootNode, CacheContainer containerFacade) {
      Object dataId = rootNode.getIdGetter().apply(null);
      CacheKeyImpl key = new CacheKeyImpl(rootNode.getDataType(), dataId);
      return new HitEntryCollectorImpl(new ReqNodeImpl(rootNode, key, containerFacade));
    }
  }

  Collection<CacheEntry> collect();
}
