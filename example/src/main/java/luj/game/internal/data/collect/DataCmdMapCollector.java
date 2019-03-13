package luj.game.internal.data.collect;

import java.util.Map;
import luj.game.internal.data.DataCmdEntry;
import org.omg.CORBA.NO_IMPLEMENT;

public interface DataCmdMapCollector {

  interface Factory {

    static DataCmdMapCollector create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  Map<Class<?>, DataCmdEntry> collect();
}
