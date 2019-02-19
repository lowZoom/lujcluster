package luj.cluster.example.server.module.login;

import akka.event.LoggingAdapter;
import luj.cluster.api.message.NodeMessageHandler;
import org.springframework.stereotype.Component;

@Component
final class LoginHandler implements NodeMessageHandler<LoginMsg> {

  @Override
  public void onHandle(Context<LoginMsg> ctx) {
    LoginMsg msg = ctx.getMessage();

    LoggingAdapter log = ctx.getLogger();
    log.info("玩家<{}>请求登录", msg.getAccount());
  }
}
