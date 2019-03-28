package luj.cache.internal.request.request.result;

import luj.cache.api.listener.CacheReadyListener;

final class ReadyContextImpl implements CacheReadyListener.Context {

  ReadyContextImpl(Object reqParam, Object reqResult) {
    _reqParam = reqParam;
    _reqResult = reqResult;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getRequestParam() {
    return (T) _reqParam;
  }

  @Override
  public Object getRequestResult() {
    return _reqResult;
  }

  private final Object _reqParam;

  private final Object _reqResult;
}
