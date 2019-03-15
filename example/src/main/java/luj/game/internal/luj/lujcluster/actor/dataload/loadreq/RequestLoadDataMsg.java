package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

public class RequestLoadDataMsg {

  public RequestLoadDataMsg(Class<?> dataType, String dataId) {
    _dataType = dataType;
    _dataId = dataId;
  }

  public Class<?> getDataType() {
    return _dataType;
  }

  public String getDataId() {
    return _dataId;
  }

  private final Class<?> _dataType;

  private final String _dataId;
}
