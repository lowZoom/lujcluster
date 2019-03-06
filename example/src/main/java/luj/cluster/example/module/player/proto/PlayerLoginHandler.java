package luj.cluster.example.module.player.proto;

import luj.cluster.example.module.player.event.PlayerLoginEvent;
import luj.game.api.proto.GameProtoHandler;
import org.springframework.stereotype.Component;

@Component
final class PlayerLoginHandler implements GameProtoHandler<PlayerLoginMsg> {

  @Override
  public void onHandle(Context<PlayerLoginMsg> ctx) {
    PlayerLoginMsg proto = ctx.getCurrentProto();

    System.out.println("玩家完成账号登录，开始进行角色登录：" + proto.getCharUid());

    //TODO: 读取玩家基本数据




    ctx.fireEvent(new PlayerLoginEvent(proto.getCharUid()));
  }
}
