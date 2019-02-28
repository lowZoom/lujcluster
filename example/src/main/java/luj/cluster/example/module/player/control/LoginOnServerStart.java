package luj.cluster.example.module.player.control;

import luj.game.api.start.GameStartListener;
import org.springframework.stereotype.Component;

@Component
final class LoginOnServerStart implements GameStartListener {

  /**
   * @see luj.cluster.example.module.account.proto.AccountLoginHandler#onHandle
   */
  @Override
  public void onStart(Context ctx) {
    System.out.println("游戏服启动，准备发登录请求。。");

  }
}
