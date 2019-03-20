package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import org.springframework.context.ApplicationContext;

final class ClusterSessionFactoryImpl implements ClusterSessionFactory {

  ClusterSessionFactoryImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public ClusterSession create() {
    return new ClusterSessionImpl(_appContext);
  }

  private final ApplicationContext _appContext;
}
