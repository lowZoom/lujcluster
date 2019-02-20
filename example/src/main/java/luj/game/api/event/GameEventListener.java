package luj.game.api.event;

public interface GameEventListener<E> {

  interface Context<E> {


  }

  void onEvent(Context<E> ctx);
}
