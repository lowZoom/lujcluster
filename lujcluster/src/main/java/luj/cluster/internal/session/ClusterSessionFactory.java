package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import org.springframework.context.ApplicationContext;

public class ClusterSessionFactory {

  public ClusterSessionFactory(ApplicationContext appContext) {
    _appContext = appContext;
  }

  public ClusterSession create() {
    ClusterSessionImpl session = new ClusterSessionImpl();
    session._appContext = _appContext;
    return session;
  }

  private final ApplicationContext _appContext;
}
