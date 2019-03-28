package luj.cache.internal.request.queue;

import luj.cache.internal.request.CacheRequestImpl;
import luj.cache.internal.request.tree.RequestNodeState;

public class RequestQueueElem {

  public RequestQueueElem(CacheRequestImpl request,
      RequestNodeState reqRootNode, Object reqParam) {
    _request = request;
    _reqRootNode = reqRootNode;
    _reqParam = reqParam;
  }

  public CacheRequestImpl getRequest() {
    return _request;
  }

  public RequestNodeState getReqRootNode() {
    return _reqRootNode;
  }

  public Object getReqParam() {
    return _reqParam;
  }

  private final CacheRequestImpl _request;
  private final RequestNodeState _reqRootNode;

  private final Object _reqParam;
}
