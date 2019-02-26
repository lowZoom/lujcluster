package luj.cluster.example.module.account.control.boot;

import luj.cluster.example.module.account.proto.AccountLoginReq;
import luj.game.api.start.GameStartListener;
import org.springframework.stereotype.Component;

@Component
final class AccountOnServerStart implements GameStartListener {

  @Override
  public void onStart(Context ctx) {
    System.out.println("登录服启动。。。");

    AccountLoginReq req = new AccountLoginReq("测试账号1");
    ctx.sendServerMessage(req);
  }
}
