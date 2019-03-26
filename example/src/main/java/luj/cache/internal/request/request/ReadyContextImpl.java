package luj.cache.internal.request.request;

import luj.cache.api.listener.CacheReadyListener.Context;
import luj.cache.api.listener.CacheReadyListener.ResultBuilder;

final class ReadyContextImpl implements Context {

  ReadyContextImpl(Object reqParam) {
    _reqParam = reqParam;
  }

  @Override
  public <T> T getRequestParam() {
    return (T) _reqParam;
  }

  @Override
  public ResultBuilder getResultBuilder() {
    return null;
  }

  private final Object _reqParam;
}
