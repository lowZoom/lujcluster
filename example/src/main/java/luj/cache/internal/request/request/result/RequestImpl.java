package luj.cache.internal.request.request.result;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.BiConsumer;
import luj.cache.api.container.CacheEntry;
import luj.cache.internal.container.key.CacheKeyImpl;
import luj.cache.internal.request.request.Requesting;
import luj.cache.internal.request.tree.RequestNodeState;

final class RequestImpl implements RequestSuccessFinisherImpl.Request {

  RequestImpl(Object reqResult, RequestNodeState nodeState,
      Requesting requesting) {
    _reqResult = reqResult;
    _nodeState = nodeState;
    _requesting = requesting;
  }

  @Override
  public RequestSuccessFinisherImpl.Field getResultField() {
    BiConsumer<Object, Object> setter = _nodeState.getResultFieldSetter();
    if (setter == null) {
      return null;
    }

    Object id = _nodeState.getIdGetter().apply(null);
    CacheEntry entry = _requesting.getContainerFacade().getEntry(new CacheKeyImpl(_nodeState.getDataType(), id));
    return (setter == null) ? null : new FieldImpl(_nodeState, _reqResult, entry);
  }

  @Override
  public List<RequestSuccessFinisherImpl.Request> getChildren() {
    return ImmutableList.of();
  }

  @Override
  public void fireReady() {
    ReadyContextImpl ctx = new ReadyContextImpl(_requesting.getRequestParam(), _reqResult);
    _requesting.getContainerState().getBeanCollect().getRequestReadyListener().onReady(ctx);
  }

  private final Object _reqResult;

  private final RequestNodeState _nodeState;

  private final Requesting _requesting;
}
