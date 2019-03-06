package luj.cluster.example.module.player.control.login;

import luj.cluster.example.module.player.data.PlayerDat;
import luj.game.api.data.PlayerDataCommand;

final class PlayerLoginCmd implements PlayerDataCommand {

  interface LoadResult {

    PlayerDat playerData();
  }


  @Override
  public void execute(Context ctx) {



  }
}
