package luj.cluster.example;

import luj.cluster.example.core.ExampleInjectConf;
import luj.game.api.Jamver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class Main {

  public static void main(String[] args) {
    try (AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(ExampleInjectConf.class)) {
      new Main(ctx).run();
    }
  }

  private Main(ApplicationContext appContext) {
    _appContext = appContext;
  }

  private void run() {
    System.out.println(1);
    Jamver.start(_appContext);
    System.out.println(2);
  }

  private final ApplicationContext _appContext;
}
