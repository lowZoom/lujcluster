package luj.cache.internal.request.request;

import luj.cache.api.listener.CacheReadyListener;

final class ReadyContextImpl implements CacheReadyListener.Context {

  ReadyContextImpl(Object reqParam, CacheReadyListener.ResultBuilder resultBuilder) {
    _reqParam = reqParam;
    _resultBuilder = resultBuilder;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getRequestParam() {
    return (T) _reqParam;
  }

  @Override
  public CacheReadyListener.ResultBuilder getResultBuilder() {
    return _resultBuilder;
  }

  private final Object _reqParam;

  private final CacheReadyListener.ResultBuilder _resultBuilder;
}
