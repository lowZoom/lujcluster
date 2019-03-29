package luj.game.internal.luj.lujcluster.actor.gameplay.data.load.loadreq;


import luj.cache.api.container.CacheKey;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.loadrsp.FinishLoadDataMsg;

final class FinishItemImpl implements FinishLoadDataMsg.FinishItem {

  FinishItemImpl(CacheKey key, Object data) {
    _key = key;
    _data = data;
  }

  @Override
  public CacheKey getKey() {
    return _key;
  }

  @Override
  public boolean isPresent() {
    return _data != null;
  }

  @Override
  public Object getData() {
    return _data;
  }

  private final CacheKey _key;

  private final Object _data;
}
