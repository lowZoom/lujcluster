package luj.cache.api.listener;

/**
 * 缓存里还没对应缓存项时触发
 */
public interface CacheMissListener<K> {

  interface Context {

    <K> K getKey(CacheMissListener<K> listener);

    <T> T getApplicationBean();
  }

  void onMiss(Context ctx);
}
