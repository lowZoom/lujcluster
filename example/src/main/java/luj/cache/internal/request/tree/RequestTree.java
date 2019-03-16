package luj.cache.internal.request.tree;

import java.util.List;
import luj.cache.api.container.CacheContainer;

public interface RequestTree {

  interface Node {

    CacheContainer getCacheContainer();

    Object getCacheKey();

    Node getParentNode();

    List<Node> getChildNodes();
  }

  Node getRoot();
}
