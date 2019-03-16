package luj.cache.api.container;

public interface CacheKey<T> {

  Class<?> getDataType();

  T getDataId();
}
