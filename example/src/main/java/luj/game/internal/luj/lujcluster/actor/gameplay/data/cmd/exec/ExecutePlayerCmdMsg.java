package luj.game.internal.luj.lujcluster.actor.gameplay.data.cmd.exec;

import java.util.List;
import luj.cache.api.container.CacheEntry;
import luj.game.api.data.PlayerDataCommand;

public class ExecutePlayerCmdMsg {

  public ExecutePlayerCmdMsg(PlayerDataCommand<?> dataCommand, Object dataResult,
      List<CacheEntry> entryList) {
    _dataCommand = dataCommand;
    _dataResult = dataResult;
    _entryList = entryList;
  }

  public PlayerDataCommand<?> getDataCommand() {
    return _dataCommand;
  }

  public Object getDataResult() {
    return _dataResult;
  }

  public List<CacheEntry> getEntryList() {
    return _entryList;
  }

  private final PlayerDataCommand<?> _dataCommand;
  private final Object _dataResult;

  private final List<CacheEntry> _entryList;
}
