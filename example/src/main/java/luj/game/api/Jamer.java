package luj.game.api;

import luj.game.internal.start.GameServerStarter;
import org.springframework.context.ApplicationContext;

public enum Jamer {
  ;

  public static void start(ApplicationContext appContext) {
    GameServerStarter.get(appContext).start();
  }
}
