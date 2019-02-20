package luj.game.api.data;

public interface GameDataCommand<D> {

  interface Context<D> {

    D getData();

    <T> T createData(Class<T> dataType);
  }

  void execute(Context<D> ctx);
}
