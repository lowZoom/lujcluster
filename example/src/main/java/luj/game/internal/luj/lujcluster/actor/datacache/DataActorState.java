package luj.game.internal.luj.lujcluster.actor.datacache;

import java.util.Map;
import luj.cache.api.container.CacheContainer;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.internal.data.DataCmdEntry;
import luj.persist.api.PersistSession;

public class DataActorState {

  public DataActorState(CacheContainer dataCache,
      Map<Class<?>, DataCmdEntry> commandMap, PersistSession lujpersist) {
    _dataCache = dataCache;
    _commandMap = commandMap;
    _lujpersist = lujpersist;
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

  public PersistSession getLujpersist() {
    return _lujpersist;
  }

  private ActorPreStartHandler.Actor _loadRef;

  private final CacheContainer _dataCache;
  private final Map<Class<?>, DataCmdEntry> _commandMap;

  private final PersistSession _lujpersist;
}
