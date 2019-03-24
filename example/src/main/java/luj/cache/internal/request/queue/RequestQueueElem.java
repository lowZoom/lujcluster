package luj.cache.internal.request.queue;

import luj.cache.internal.request.tree.RequestNodeState;

public class RequestQueueElem {

  public RequestQueueElem(RequestNodeState reqRootNode, Object reqParam) {
    _reqRootNode = reqRootNode;
    _reqParam = reqParam;
  }

  public RequestNodeState getReqRootNode() {
    return _reqRootNode;
  }

  public Object getReqParam() {
    return _reqParam;
  }

  private final RequestNodeState _reqRootNode;

  private final Object _reqParam;
}
