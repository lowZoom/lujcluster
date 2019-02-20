package luj.game.internal.start;

import luj.cluster.api.LujCluster;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class GameServerStarterImpl implements GameServerStarter {

  GameServerStarterImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start() {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_appContext);

      resultCtx.register(InjectConf.class);
      resultCtx.refresh();

      LujCluster.start(resultCtx);
    }
  }

  private final ApplicationContext _appContext;
}
