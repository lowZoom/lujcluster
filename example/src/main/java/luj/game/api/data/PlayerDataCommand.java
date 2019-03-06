package luj.game.api.data;

public interface PlayerDataCommand<D> {

  interface Context<D> {

    D data();

    Player player();
  }

  interface Player {

  }

  void execute(Context<D> ctx);
}
