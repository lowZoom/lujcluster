package luj.game.internal.luj.lujcluster.actor.cmd.exec;

import luj.cache.api.listener.CacheReadyListener;
import luj.game.api.data.PlayerDataCommand;

public class ExecutePlayerCmdMsg {

  public ExecutePlayerCmdMsg(PlayerDataCommand<?> command,
      CacheReadyListener.ResultBuilder resultBuilder) {
    _command = command;
    _resultBuilder = resultBuilder;
  }

  public PlayerDataCommand<?> getCommand() {
    return _command;
  }

  public CacheReadyListener.ResultBuilder getResultBuilder() {
    return _resultBuilder;
  }

  private final PlayerDataCommand<?> _command;

  private final CacheReadyListener.ResultBuilder _resultBuilder;
}
