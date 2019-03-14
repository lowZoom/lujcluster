package luj.game.internal.luj.lujcluster.actor.datacache;

import java.util.Map;
import luj.cache.api.container.CacheContainer;
import luj.game.internal.data.DataCmdEntry;

public class DataActorState {

  public DataActorState(CacheContainer dataCache, Map<Class<?>, DataCmdEntry> commandMap) {
    _dataCache = dataCache;
    _commandMap = commandMap;
  }

  public CacheContainer getDataCache() {
    return _dataCache;
  }

  public Map<Class<?>, DataCmdEntry> getCommandMap() {
    return _commandMap;
  }

  private final CacheContainer _dataCache;

  private final Map<Class<?>, DataCmdEntry> _commandMap;
}
