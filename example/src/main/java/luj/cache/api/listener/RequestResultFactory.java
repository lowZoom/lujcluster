package luj.cache.api.listener;

public interface RequestResultFactory {

  interface Context {

    Class<?> getResultType();

    <T> T getRequestParam();
  }

  Object create(Context ctx);
}
