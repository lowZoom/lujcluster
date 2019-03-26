package luj.cache.api.listener;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;

/**
 * 缓存里还没对应缓存项时触发
 */
public interface CacheMissListener {

  interface Context {

    CacheKey<?> getMissingKey();

    <T> T getRequestParam();

    CacheContainer getCacheContainer();
  }

  void onMiss(Context ctx);
}
