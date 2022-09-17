package luj.cluster.internal.node.shutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Safe<T> {

  public interface Caller<T> {

    void call(T obj) throws Exception;
  }

  public static <T> Safe<T> obj(T obj) {
    return new Safe<>(obj);
  }

  public Safe(T obj) {
    _obj = obj;
  }

  public void call(Caller<T> caller) {
    if (_obj == null) {
      return;
    }

    try {
      caller.call(_obj);

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(Safe.class);

  private final T _obj;
}
