package luj.game.api.proto;

public interface GameProtoHandler<P> {

  interface Context<P> {

    P getCurrentProto();

    void sendProto(Object proto);

    void fireEvent(Object event);
  }

  void onHandle(Context<P> ctx);
}
