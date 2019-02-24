package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import org.springframework.context.ApplicationContext;

final class ClusterSessionFactoryImpl implements ClusterSessionFactory {

  ClusterSessionFactoryImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public ClusterSession create() {
    ClusterNodeStarter.Factory.create(_appContext).start();

    return null;
  }

  private final ApplicationContext _appContext;
}
