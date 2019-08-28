package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import org.springframework.context.ApplicationContext;

final class ClusterSessionImpl implements ClusterSession {

  ClusterSessionImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void startNode(String host, int port, String seedAddr, Object startParam) {
    ClusterNodeStarter.Factory.create(_appContext, host, port, seedAddr, startParam).start();
  }

  private final ApplicationContext _appContext;
}
