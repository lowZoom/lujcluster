package luj.cache.internal.container.request;

import java.util.ArrayList;
import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.request.tree.RequestTree;
import luj.cache.internal.request.tree.walk.RequestTreeWalker;
import org.omg.CORBA.NO_IMPLEMENT;

final class CacheDataRequestorImpl implements CacheDataRequestor {

  CacheDataRequestorImpl(CacheContainerState containerState, RequestTree requestTree) {
    _containerState = containerState;
    _requestTree = requestTree;
  }

  @Override
  public void request() {
    List<Object> missList = collectMiss();

    if (!missList.isEmpty()) {
      for (Object key : missList) {
        triggerListener(key);
      }

      return;
    }

    _containerState.getRequestReadyListener().onReady(null);
  }

  private List<Object> collectMiss() {
    List<Object> missList = new ArrayList<>();
    RequestTreeWalker.Factory.create().walk(_requestTree, ctx -> onMissWalk(ctx, missList));
    return missList;
  }

  private void onMissWalk(RequestTreeWalker.StepContext ctx, List<Object> missList) {
    RequestTree.Node node = ctx.getNode();

    Object cacheKey = node.getCacheKey();
    CacheContainer cacheContainer = node.getCacheContainer();
    if (cacheContainer.contains(cacheKey)) {
      return;
    }

    missList.add(cacheKey);
  }

  private void triggerListener(Object key) {
    throw new NO_IMPLEMENT("triggerListener尚未实现");
  }

  private final CacheContainerState _containerState;

  private final RequestTree _requestTree;
}
