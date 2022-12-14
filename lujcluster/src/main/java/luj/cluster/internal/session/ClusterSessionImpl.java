package luj.cluster.internal.session;

import java.util.function.Function;
import luj.cluster.api.ClusterSession;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import luj.cluster.internal.node.start.config.StartConfigMaker;
import org.springframework.context.ApplicationContext;

final class ClusterSessionImpl implements ClusterSession {

  @Override
  public ClusterNode startNode(Function<Start, Start> startConfig) {
    ClusterNodeStarter.Config config = new StartConfigMaker(startConfig).make();
    return new ClusterNodeStarter(_appContext, config).start();
  }

  ApplicationContext _appContext;
}
