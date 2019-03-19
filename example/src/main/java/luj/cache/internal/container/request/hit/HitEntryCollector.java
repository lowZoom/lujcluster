package luj.cache.internal.container.request.hit;

import java.util.List;
import luj.cache.api.container.CacheEntry;
import org.omg.CORBA.NO_IMPLEMENT;

public interface HitEntryCollector {

  interface Factory {

    static HitEntryCollector create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  List<CacheEntry> collect();
}
