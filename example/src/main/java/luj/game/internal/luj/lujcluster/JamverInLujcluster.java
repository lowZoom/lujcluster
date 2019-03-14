package luj.game.internal.luj.lujcluster;

import luj.cache.api.CacheSession;

public class JamverInLujcluster {

  public JamverInLujcluster(CacheSession lujcache) {
    _lujcache = lujcache;
  }

  public CacheSession getLujcache() {
    return _lujcache;
  }

  private final CacheSession _lujcache;
}
