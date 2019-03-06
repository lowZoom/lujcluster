package luj.cluster.example.module.scene.control;

import luj.cluster.example.module.player.event.PlayerLoginEvent;
import luj.game.api.event.GameEventListener;
import org.springframework.stereotype.Component;

@Component
final class SceneOnLogin implements GameEventListener<PlayerLoginEvent> {

  @Override
  public void onEvent(Context<PlayerLoginEvent> ctx) {


  }
}
