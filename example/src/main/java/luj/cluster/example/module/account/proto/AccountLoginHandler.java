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

    //TODO: 读取数据库时，数据库中有使用者信息，相当于业务锁，一个节点借数据时等待的锁还可以包括远程锁

    //TODO: 应该不会直接发消息，而是会读取数据库进行账号登陆逻辑
    ctx.sendProto(new LoginMsg("角色id"));
  }
}
