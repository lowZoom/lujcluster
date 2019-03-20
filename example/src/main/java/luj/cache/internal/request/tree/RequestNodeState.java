package luj.cache.internal.request.tree;

import java.util.function.Function;

public class RequestNodeState {

  public RequestNodeState(Class<?> dataType, Function<Object, Object> idGetter) {
    _dataType = dataType;
    _idGetter = idGetter;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public Function<Object, Object> getIdGetter() {
    return _idGetter;
  }

  private final Class<?> _dataType;

  private final Function<Object, Object> _idGetter;
}
