package luj.cluster.example.module.account.proto;

import luj.cluster.example.module.login.proto.LoginMsg;
import luj.game.api.proto.GameProtoHandler;
import org.springframework.stereotype.Component;

@Component
final class AccountLoginHandler implements GameProtoHandler<AccountLoginReq> {

  /**
   * @see luj.cluster.example.module.login.proto.LoginHandler#onHandle
   */
  @Override
  public void onHandle(Context<AccountLoginReq> ctx) {
    AccountLoginReq req = ctx.getCurrentProto();
    System.out.println("在登录服进行账号登录: " + req.getAccount());

    ctx.sendProto(new LoginMsg("角色id"));
  }
}
