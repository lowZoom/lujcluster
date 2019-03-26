package luj.game.internal.luj.lujcluster;

import luj.cache.api.CacheSession;
import luj.persist.api.PersistSession;

public class JambeanInLujcluster {

  public JambeanInLujcluster(CacheSession lujcache, PersistSession lujpersist) {
    _lujcache = lujcache;
    _lujpersist = lujpersist;
  }

  public CacheSession getLujcache() {
    return _lujcache;
  }

  public PersistSession getLujpersist() {
    return _lujpersist;
  }

  private final CacheSession _lujcache;

  private final PersistSession _lujpersist;
}
