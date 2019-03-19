package luj.game.internal.luj.lujcluster;

import luj.cache.api.container.CacheContainer;

public class JambeanInLujcluster {

  public JambeanInLujcluster(CacheContainer<String> cache) {
    _cache = cache;
  }

  public CacheContainer<String> getCache() {
    return _cache;
  }

  private final CacheContainer<String> _cache;
}
