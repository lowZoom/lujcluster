package luj.game.internal.start;

import luj.cache.api.CacheSession;
import luj.cache.api.LujCache;
import luj.cluster.api.LujCluster;
import luj.game.internal.luj.lujcluster.JambeanInLujcluster;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class GameServerStarterImpl implements GameServerStarter {

  GameServerStarterImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  /**
   * @see luj.game.internal.luj.lujcluster.OnLujclusterStart#onStart
   */
  @Override
  public void start() {
    try (AnnotationConfigApplicationContext jamCtx = new AnnotationConfigApplicationContext()) {
      jamCtx.setParent(_appContext);

      jamCtx.register(InjectConf.class);
      jamCtx.refresh();

      //TODO: 读取配置，确定服务器id

      LujCluster.start(jamCtx).startNode(
          "192.168.1.99", 2552, "192.168.1.99:2552", createStartParam(jamCtx));
    }
  }

  private JambeanInLujcluster createStartParam(ApplicationContext jamCtx) {
    CacheSession lujcache = LujCache.start(jamCtx);
    return new JambeanInLujcluster(lujcache);
  }

  private final ApplicationContext _appContext;
}
