package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

import luj.game.internal.luj.lujcache.LujcacheKey;

public class RequestLoadDataMsg {

  public RequestLoadDataMsg(LujcacheKey dataKey) {
    _dataKey = dataKey;
  }

  public LujcacheKey getDataKey() {
    return _dataKey;
  }

  private final LujcacheKey _dataKey;
}
