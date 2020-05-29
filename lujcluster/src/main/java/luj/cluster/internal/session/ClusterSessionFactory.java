package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import org.springframework.context.ApplicationContext;

public class ClusterSessionFactory {

  public ClusterSessionFactory(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public ClusterSession create() {
    return new ClusterSessionImpl(_appContext);
  }

  private final ApplicationContext _appContext;
}
