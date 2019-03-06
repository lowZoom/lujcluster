package luj.game.api.data;

public interface PlayerDataCommand {

  interface Context {

    Player getPlayer();
  }

  interface Player {

  }

  void execute(Context ctx);
}
