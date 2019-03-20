package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

import luj.cache.api.container.CacheKey;

public class RequestLoadDataMsg {

  public RequestLoadDataMsg(CacheKey<?> dataKey) {
    _dataKey = dataKey;
  }

  public CacheKey<?> getDataKey() {
    return _dataKey;
  }

  private final CacheKey<?> _dataKey;
}
