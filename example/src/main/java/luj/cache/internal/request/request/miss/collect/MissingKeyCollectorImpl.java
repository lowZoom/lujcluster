package luj.cache.internal.request.request.miss.collect;

import java.util.ArrayList;
import java.util.List;
import luj.cache.api.container.CacheKey;

final class MissingKeyCollectorImpl implements MissingKeyCollector {

  MissingKeyCollectorImpl(RequestNode requestRoot) {
    _requestRoot = requestRoot;
  }

  @Override
  public List<CacheKey> collect() {
    List<CacheKey> missList = new ArrayList<>();
    walkReq(_requestRoot, missList);
    return missList;
  }

  private void walkReq(RequestNode node, List<CacheKey> missList) {
    Entry entry = node.getCacheEntry();
    if (entry.isMissing()) {
      missList.add(entry.getKey());
    }

    for (RequestNode child : node.getChildren()) {
      walkReq(child, missList);
    }
  }

  interface RequestNode {

    Entry getCacheEntry();

    List<RequestNode> getChildren();
  }

  interface Entry {

    boolean isMissing();

    CacheKey getKey();
  }

  private final RequestNode _requestRoot;
}
