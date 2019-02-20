package luj.cluster.example.module.account.control;

import luj.game.api.start.GameStartListener;
import org.springframework.stereotype.Component;

@Component
final class AccountOnServerStart implements GameStartListener {

  @Override
  public void onStart(Context ctx) {
    System.out.println("登录服启动。。。");
  }
}
