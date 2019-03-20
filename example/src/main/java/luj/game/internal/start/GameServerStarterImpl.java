package luj.game.internal.start;

import luj.cache.api.LujCache;
import luj.cache.api.container.CacheContainer;
import luj.cluster.api.LujCluster;
import luj.game.internal.luj.lujcluster.JambeanInLujcluster;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class GameServerStarterImpl implements GameServerStarter {

  GameServerStarterImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start() {
    try (AnnotationConfigApplicationContext jamCtx = new AnnotationConfigApplicationContext()) {
      jamCtx.setParent(_appContext);

      jamCtx.register(InjectConf.class);
      jamCtx.refresh();

      LujCluster.start(jamCtx)
          .startNode(createStartParam(jamCtx));
    }
  }

  private JambeanInLujcluster createStartParam(ApplicationContext jamCtx) {
    CacheContainer<String> cache = LujCache.createCache(jamCtx);
    return new JambeanInLujcluster(cache);
  }

  private final ApplicationContext _appContext;
}
