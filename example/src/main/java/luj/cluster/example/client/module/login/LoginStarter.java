package luj.cluster.example.client.module.login;

import luj.cluster.api.start.NodeStartListener;
import org.springframework.stereotype.Component;

@Component
final class LoginStarter implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    System.out.println("登录服启动。。");
  }
}
