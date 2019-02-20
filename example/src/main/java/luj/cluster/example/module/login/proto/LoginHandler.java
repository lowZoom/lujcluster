package luj.cluster.example.module.login.proto;

import luj.cluster.example.module.login.event.LoginEvent;
import luj.game.api.proto.GameProtoHandler;
import org.springframework.stereotype.Component;

@Component
final class LoginHandler implements GameProtoHandler<LoginMsg> {

  @Override
  public void onHandle(Context<LoginMsg> ctx) {
    LoginMsg proto = ctx.getCurrentProto();

    System.out.println("玩家完成账号登录，开始进行角色登录：" + proto.getCharUid());

    ctx.fireEvent(new LoginEvent(proto.getCharUid()));
  }
}
