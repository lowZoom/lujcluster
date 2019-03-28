package luj.cache.internal.request.request;

import luj.cache.api.container.CacheContainer;
import luj.cache.internal.container.CacheContainerState;

public class Requesting {

  public Requesting(CacheContainer containerFacade,
      CacheContainerState containerState, Object requestParam) {
    _containerFacade = containerFacade;
    _containerState = containerState;
    _requestParam = requestParam;
  }

  public CacheContainer getContainerFacade() {
    return _containerFacade;
  }

  public CacheContainerState getContainerState() {
    return _containerState;
  }

  public Object getRequestParam() {
    return _requestParam;
  }

  private final CacheContainer _containerFacade;

  private final CacheContainerState _containerState;

  private final Object _requestParam;
}
