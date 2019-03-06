package luj.game.api.proto;

import luj.game.api.data.PlayerDataCommand;

/**
 * 处理玩家客户端发来的协议
 */
public interface GameProtoHandler<P> {

  interface Context<P> {

    P proto();

    Player player();

    void sendProto(Object proto);

    void fireEvent(Object event);
  }

  interface Player {

    void executeDataCommand(Class<? extends PlayerDataCommand> cmdType);
  }

  void onHandle(Context<P> ctx);
}
