package luj.cache.internal.container.key;

import luj.cache.api.container.CacheKey;

@Deprecated
public class CacheKeyImpl<T> implements CacheKey<T> {

  public CacheKeyImpl(Class<?> dataType, Object dataId) {
    _dataType = dataType;
    _dataId = dataId;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public T getDataId() {
    return (T) _dataId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    CacheKeyImpl that = (CacheKeyImpl) obj;
    return _dataType == that._dataType
        && _dataId.equals(that._dataId);
  }

  @Override
  public int hashCode() {
    int result = _dataType.hashCode();
    result = 31 * result + _dataId.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "[" + _dataType.getSimpleName() + "#" + _dataId + "}";
  }

  private final Class<?> _dataType;

  private final Object _dataId;
}
