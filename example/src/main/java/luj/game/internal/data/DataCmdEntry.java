package luj.game.internal.data;

import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataLoad;

public interface DataCmdEntry {

  PlayerDataLoad<?> getRequestor();

  PlayerDataCommand<?> getCommand();
}
