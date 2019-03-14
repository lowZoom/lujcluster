package luj.cache.api.container;

/**
 * 缓存里还没对应缓存项时触发
 */
public interface CacheMissListener<K> {

  interface Context {

    <K> K getKey(CacheMissListener<K> listener);
  }

  void onMiss(Context ctx);
}
