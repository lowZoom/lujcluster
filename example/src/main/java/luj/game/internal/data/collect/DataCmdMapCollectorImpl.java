package luj.game.internal.data.collect;

import java.util.List;
import java.util.Map;
import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;

final class DataCmdMapCollectorImpl implements DataCmdMapCollector {



  @Override
  public Map<Class<?>, DataCmdEntry> collect() {
    return null;
  }

  private final List<PlayerDataCommand<?>> _playerDataCmdList;

  private final List<PlayerDataLoad<?>> _playerDataReqList;
}
