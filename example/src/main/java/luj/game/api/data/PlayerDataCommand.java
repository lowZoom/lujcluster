package luj.game.api.data;

public interface PlayerDataCommand<D> {

  interface Context {

    <D> D data(PlayerDataCommand<D> cmd);

    Player player();
  }

  interface Player {

  }

  void execute(Context ctx);
}
