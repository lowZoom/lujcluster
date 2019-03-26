package luj.game.internal.luj.lujcluster;

import luj.cache.api.CacheSession;

public class JambeanInLujcluster {

  public JambeanInLujcluster(CacheSession lujcache) {
    _lujcache = lujcache;
  }

  public CacheSession getLujcache() {
    return _lujcache;
  }

  private final CacheSession _lujcache;

//  private final PersistSession _lujpersist;
}
