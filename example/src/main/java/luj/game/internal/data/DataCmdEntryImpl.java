package luj.game.internal.data;

import luj.game.api.data.PlayerDataLoad;

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
