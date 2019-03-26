package luj.cache.internal.request.request.hit;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;

final class HitEntryCollectorImpl implements HitEntryCollector {

  HitEntryCollectorImpl(ReqNode rootNode) {
    _rootNode = rootNode;
  }

  @Override
  public Collection<CacheEntry> collect() {
    Map<CacheKey, CacheEntry> resultMap = new HashMap<>();
    walkReq(_rootNode, resultMap);
    return resultMap.values();
  }

  private void walkReq(ReqNode node, Map<CacheKey, CacheEntry> resultMap) {
    resultMap.put(node.getKey(), node.getEntry());

    for (ReqNode child : node.getChildren()) {
      walkReq(child, resultMap);
    }
  }

  interface ReqNode {

    CacheKey getKey();

    CacheEntry getEntry();

    List<ReqNode> getChildren();
  }

  private final ReqNode _rootNode;
}
