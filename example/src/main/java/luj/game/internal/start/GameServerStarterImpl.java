package luj.game.internal.start;

import luj.cache.api.CacheSession;
import luj.cache.api.LujCache;
import luj.cluster.api.LujCluster;
import luj.game.internal.luj.lujcluster.JambeanInLujcluster;
import luj.persist.api.LujPersist;
import luj.persist.api.PersistSession;
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
    CacheSession lujcache = LujCache.start(jamCtx);
    PersistSession lujpersist = LujPersist.start(jamCtx);

    return new JambeanInLujcluster(lujcache, lujpersist);
  }

  private final ApplicationContext _appContext;
}
