package luj.game.internal.data.collect;

import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;

final class DataCmdEntryImpl implements DataCmdEntry {

  DataCmdEntryImpl(PlayerDataLoad<?> loadReq) {
    _loadReq = loadReq;
  }

  @Override
  public PlayerDataLoad<?> getRequestor() {
    return _loadReq;
  }

  private final PlayerDataLoad<?> _loadReq;
}