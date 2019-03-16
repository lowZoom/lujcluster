package luj.cache.internal.container.request.miss;

import java.util.ArrayList;
import java.util.List;

final class MissingEntryCollectorImpl implements MissingEntryCollector {

  MissingEntryCollectorImpl(RequestNode requestRoot) {
    _requestRoot = requestRoot;
  }

  @Override
  public List<Object> collect() {
    List<Object> missList = new ArrayList<>();
    walkRequest(_requestRoot, missList);
    return missList;
  }

  private void walkRequest(RequestNode node, List<Object> missList) {
    Entry entry = node.getCacheEntry();
    if (entry.isMissing()) {
      missList.add(entry.getKey());
    }

    for (RequestNode child : node.getChildren()) {
      walkRequest(child, missList);
    }
  }

  interface RequestNode {

    Entry getCacheEntry();

    List<RequestNode> getChildren();
  }

  interface Entry {

    boolean isMissing();

    Object getKey();
  }

  private final RequestNode _requestRoot;
}
