package luj.cache.internal.request.tree;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class RequestNodeState {

  public RequestNodeState(Class<?> dataType,
      Function<Object, Object> idGetter,
      BiConsumer<Object, Object> resultFieldSetter) {
    _dataType = dataType;
    _idGetter = idGetter;
    _resultFieldSetter = resultFieldSetter;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public Function<Object, Object> getIdGetter() {
    return _idGetter;
  }

  public BiConsumer<Object, Object> getResultFieldSetter() {
    return _resultFieldSetter;
  }

  private final Class<?> _dataType;
  private final Function<Object, Object> _idGetter;

  private final BiConsumer<Object, Object> _resultFieldSetter;
}
