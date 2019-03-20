package luj.game.internal.luj.lujcache;

import luj.cluster.api.message.NodeMessageListener;
import luj.game.api.data.PlayerDataCommand;

public class JamreqInLujcache {

  public JamreqInLujcache(NodeMessageListener.Actor loadActor, PlayerDataCommand<?> dataCmd) {
    _loadActor = loadActor;
    _dataCmd = dataCmd;
  }

  public NodeMessageListener.Actor getLoadActor() {
    return _loadActor;
  }

  public PlayerDataCommand<?> getDataCmd() {
    return _dataCmd;
  }

  private final NodeMessageListener.Actor _loadActor;

  private final PlayerDataCommand<?> _dataCmd;
}
