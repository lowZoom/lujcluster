package luj.game.api.proto;

public interface GameProtoHandler<P> {

  interface Context<P> {

    P getCurrentProto();

    void sendProto(Object proto);

    void fireEvent(Object event);
  }

  interface Player {

    void executeDataCommand();
  }

  void onHandle(Context<P> ctx);
}
