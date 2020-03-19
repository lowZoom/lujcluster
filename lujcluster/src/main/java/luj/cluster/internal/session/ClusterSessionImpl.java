package luj.cluster.internal.session;

import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import org.springframework.context.ApplicationContext;

final class ClusterSessionImpl implements ClusterSession {

  ClusterSessionImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void startNode(String host, int port, String seedAddr, Object startParam) {
    startNode(host, port, ImmutableList.of(seedAddr), startParam);
  }

  @Override
  public void startNode(String host, int port, List<String> seedList, Object startParam) {
    ClusterNodeStarter.Factory.create(_appContext, host, port, seedList, startParam).start();
  }

  private final ApplicationContext _appContext;
}
