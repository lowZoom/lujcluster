package luj.cache.internal.request.request.hit;

import java.util.Collection;
import luj.cache.api.container.CacheEntry;
import org.omg.CORBA.NO_IMPLEMENT;

public interface HitEntryCollector {

  interface Factory {

    static HitEntryCollector create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  Collection<CacheEntry> collect();
}
