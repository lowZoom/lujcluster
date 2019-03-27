package luj.cache.internal.request;

import java.util.function.BiConsumer;
import luj.cache.api.request.CacheRequest;
import luj.cache.internal.request.tree.RequestNodeState;

@Deprecated
public class CacheRequestImpl implements CacheRequest {

  @Override
  public <T1, T2> void addNode(Class<T2> dataType, Object dataId,
      BiConsumer<T1, T2> resultFieldSetter) {
    _reqNode = new RequestNodeState(dataType, o -> dataId,
        (BiConsumer<Object, Object>) resultFieldSetter);
  }

  @Override
  public RequestNodeState getRootNode() {
    return _reqNode;
  }

  private RequestNodeState _reqNode;
}
