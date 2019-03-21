package luj.game.internal.data.collect;

import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;

final class DataCmdEntryImpl implements DataCmdEntry {

  DataCmdEntryImpl(PlayerDataLoad<?> loadReq, PlayerDataCommand<?> command) {
    _loadReq = loadReq;
    _command = command;
  }

  @Override
  public PlayerDataLoad<?> getRequestor() {
    return _loadReq;
  }

  @Override
  public PlayerDataCommand<?> getCommand() {
    return _command;
  }

  private final PlayerDataLoad<?> _loadReq;

  private final PlayerDataCommand<?> _command;
}
