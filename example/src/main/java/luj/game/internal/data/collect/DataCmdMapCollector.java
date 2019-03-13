package luj.game.internal.data.collect;

import java.util.Map;
import luj.game.internal.data.DataCmdEntry;

public interface DataCmdMapCollector {

  interface Factory {

    static DataCmdMapCollector create() {
      return new DataCmdMapCollectorImpl();
    }
  }

  Map<Class<?>, DataCmdEntry> collect();
}
