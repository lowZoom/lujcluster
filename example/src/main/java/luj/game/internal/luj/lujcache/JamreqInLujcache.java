package luj.game.internal.luj.lujcache;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.logging.Log;
import luj.game.api.data.PlayerDataCommand;

public class JamreqInLujcache {

  public JamreqInLujcache(ActorPreStartHandler.Actor loadActor, PlayerDataCommand<?> dataCmd,
      Log logger) {
    _loadActor = loadActor;
    _dataCmd = dataCmd;
    _logger = logger;
  }

  public ActorPreStartHandler.Actor getLoadActor() {
    return _loadActor;
  }

  public PlayerDataCommand<?> getDataCmd() {
    return _dataCmd;
  }

  public Log getLogger() {
    return _logger;
  }

  private final ActorPreStartHandler.Actor _loadActor;

  private final PlayerDataCommand<?> _dataCmd;

  private final Log _logger;
}
