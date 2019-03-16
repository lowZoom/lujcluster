package luj.cache.internal.container.request.miss;

import java.util.List;
import org.omg.CORBA.NO_IMPLEMENT;

public interface MissingEntryCollector {

  interface Factory {

    static MissingEntryCollector create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  List<Object> collect();
}
