package luj.cache.internal.container.request.miss;

import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.internal.request.tree.RequestNodeState;

public interface MissingKeyCollector {

  interface Factory {

    static MissingKeyCollector create(CacheContainer cacheContainer, RequestNodeState reqRootNode) {
      return new MissingKeyCollectorImpl(new RequestNodeImpl(reqRootNode, cacheContainer, null));
    }
  }

  List<Object> collect();
}
