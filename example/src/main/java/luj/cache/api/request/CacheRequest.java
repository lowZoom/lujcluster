package luj.cache.api.request;

import java.util.function.BiConsumer;
import luj.cache.internal.request.tree.RequestNodeState;

public interface CacheRequest {

  <T1, T2> void addNode(Class<T2> dataType, Object dataId, BiConsumer<T1, T2> resultFieldSetter);

  RequestNodeState getRootNode();
}
