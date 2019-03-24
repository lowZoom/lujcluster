package luj.cache.internal.request.request.miss.collect;

import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.request.tree.RequestNodeState;

public interface MissingKeyCollector {

  interface Factory {

    static MissingKeyCollector create(CacheContainer cacheContainer, RequestNodeState reqRootNode) {
      return new MissingKeyCollectorImpl(new RequestNodeImpl(reqRootNode, cacheContainer, null));
    }
  }

  List<CacheKey> collect();
}
