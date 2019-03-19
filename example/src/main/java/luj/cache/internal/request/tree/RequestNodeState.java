package luj.cache.internal.request.tree;

import java.util.function.Function;
import luj.cache.api.container.CacheContainer;

public class RequestNodeState {

  public RequestNodeState(Class<?> dataType, Function<Object, Object> idGetter,
      CacheContainer cacheContainer) {
    _dataType = dataType;
    _idGetter = idGetter;
    _cacheContainer = cacheContainer;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public Function<Object, Object> getIdGetter() {
    return _idGetter;
  }

  public CacheContainer getCacheContainer() {
    return _cacheContainer;
  }

  private final Class<?> _dataType;

  private final Function<Object, Object> _idGetter;

  private final CacheContainer _cacheContainer;
}
