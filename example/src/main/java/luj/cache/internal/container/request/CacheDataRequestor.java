package luj.cache.internal.container.request;

import luj.cache.internal.request.tree.RequestTree;
import org.omg.CORBA.NO_IMPLEMENT;

public interface CacheDataRequestor {

  interface Factory {

    static CacheDataRequestor create(RequestTree tree) {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  void request();
}
