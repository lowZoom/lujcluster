package luj.game.internal.luj.lujcluster.actor.datacache;

import java.util.Map;
import luj.cache.api.container.CacheContainer;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.internal.data.DataCmdEntry;

public class DataActorState {

  public DataActorState(CacheContainer dataCache, Map<Class<?>, DataCmdEntry> commandMap) {
    _dataCache = dataCache;
    _commandMap = commandMap;
  }

  public ActorPreStartHandler.Actor getLoadRef() {
    return _loadRef;
  }

  public void setLoadRef(ActorPreStartHandler.Actor loadRef) {
    _loadRef = loadRef;
  }

  public CacheContainer getDataCache() {
    return _dataCache;
  }

  public Map<Class<?>, DataCmdEntry> getCommandMap() {
    return _commandMap;
  }

  private ActorPreStartHandler.Actor _loadRef;

  private final CacheContainer _dataCache;
  private final Map<Class<?>, DataCmdEntry> _commandMap;
}
