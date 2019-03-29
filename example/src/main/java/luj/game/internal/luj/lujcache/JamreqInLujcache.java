package luj.game.internal.luj.lujcache;

import luj.cluster.api.logging.Log;
import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorState;

public class JamreqInLujcache {

  public JamreqInLujcache(DataActorState cacheActor, PlayerDataCommand<?> dataCmd,
      Log logger) {
    _cacheActor = cacheActor;
    _dataCmd = dataCmd;
    _logger = logger;
  }

  public DataActorState getCacheActor() {
    return _cacheActor;
  }

  public PlayerDataCommand<?> getDataCmd() {
    return _dataCmd;
  }

  public Log getLogger() {
    return _logger;
  }

  private final DataActorState _cacheActor;

  private final PlayerDataCommand<?> _dataCmd;

  private final Log _logger;
}
