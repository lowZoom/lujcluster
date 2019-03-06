package luj.cluster.example.module.player.control.login;

import luj.cluster.example.module.player.data.PlayerDat;
import luj.game.api.data.PlayerDataCommand;

public class PlayerLoginCmd implements PlayerDataCommand<PlayerDat> {

//  interface LoadResult {
//
//     playerData();
//  }
//
//  static class Load implements PlayerDataLoad<LoadResult> {
//
//    @Override
//    public void load(Context<LoadResult> ctx) {
//      ctx.load(LoadResult::playerData);
//    }
//  }

  @Override
  public void execute(Context<PlayerDat> ctx) {
    PlayerDat playerDat = ctx.data();

    if (playerDat == null) {

    }
  }
}
