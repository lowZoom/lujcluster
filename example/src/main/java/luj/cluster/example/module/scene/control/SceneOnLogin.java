package luj.cluster.example.module.scene.control;

import luj.cluster.example.module.login.event.LoginEvent;
import luj.game.api.event.GameEventListener;
import org.springframework.stereotype.Component;

@Component
final class SceneOnLogin implements GameEventListener<LoginEvent> {

  @Override
  public void onEvent(Context<LoginEvent> ctx) {

  }
}
