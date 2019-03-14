package luj.game.internal.luj.lujcache;

public class LujcacheKey {

  public LujcacheKey(Class<?> dataType, String dataId) {
    _dataType = dataType;
    _dataId = dataId;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public String getDataId() {
    return _dataId;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    LujcacheKey that = (LujcacheKey) obj;
    return _dataType == that._dataType
        && _dataId.equals(that._dataId);
  }

  @Override
  public int hashCode() {
    int result = _dataType.hashCode();
    result = 31 * result + _dataId.hashCode();
    return result;
  }

  private final Class<?> _dataType;

  private final String _dataId;
}
