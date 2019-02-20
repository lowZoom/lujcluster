package luj.game.internal.start;

import org.springframework.context.ApplicationContext;

public interface GameServerStarter {

  static GameServerStarter get(ApplicationContext appContext) {
    return new GameServerStarterImpl(appContext);
  }

  void start();
}
