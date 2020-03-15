package luj.cluster.example;

import luj.cluster.example.core.ExampleInjectConf;
import luj.game.api.Jamver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class Main {

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(ExampleInjectConf.class)) {
      new Main().run(ctx);
    }
  }

  /**
   * @see luj.cluster.example.module.account.control.boot.AccountOnServerStart
   */
  private void run(ApplicationContext appContext) {
    Jamver.start(appContext);
  }
}
