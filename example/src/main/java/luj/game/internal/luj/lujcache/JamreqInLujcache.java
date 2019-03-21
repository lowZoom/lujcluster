package luj.game.internal.luj.lujcache;

import luj.cluster.api.actor.ActorPreStartHandler.Actor;
import luj.game.api.data.PlayerDataCommand;

public class JamreqInLujcache {

  public JamreqInLujcache(Actor loadActor, PlayerDataCommand<?> dataCmd) {
    _loadActor = loadActor;
    _dataCmd = dataCmd;
  }

  public Actor getLoadActor() {
    return _loadActor;
  }

  public PlayerDataCommand<?> getDataCmd() {
    return _dataCmd;
  }

  private final Actor _loadActor;

  private final PlayerDataCommand<?> _dataCmd;
}
