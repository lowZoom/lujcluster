package luj.cache.internal.container.request;

import luj.cache.internal.request.tree.RequestTreeState;
import org.omg.CORBA.NO_IMPLEMENT;

public interface CacheDataRequestor {

  interface Factory {

    static CacheDataRequestor create(RequestTreeState tree) {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  void request();
}
