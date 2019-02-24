package luj.game.api.start;

public interface GameStartListener {

  interface Context {

    void sendServerMessage(Object msg);
  }

  void onStart(Context ctx);
}
