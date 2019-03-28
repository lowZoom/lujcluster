package luj.cache.internal.request.request.result;

import luj.cache.api.listener.RequestResultFactory.Context;

final class ResultFactoryContextImpl implements Context {

  ResultFactoryContextImpl(Class<?> resultType) {
    _resultType = resultType;
  }

  @Override
  public Class<?> getResultType() {
    return _resultType;
  }

  @Override
  public <T> T getRequestParam() {
    return null;
  }

  private final Class<?> _resultType;
}
