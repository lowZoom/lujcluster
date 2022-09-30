package luj.cluster.internal.session;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Function;
import luj.cluster.api.ClusterSession;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import luj.cluster.internal.node.start.config.StartConfigMaker;
import org.springframework.context.ApplicationContext;

final class ClusterSessionImpl implements ClusterSession {

  @Override
  public void startNode(String host, int port, String seedAddr, Object startParam) {
    startNode(host, port, ImmutableList.of(seedAddr), startParam);
  }

  @Override
  public ClusterNode startNode(String host, int port, List<String> seedList, Object startParam) {
    return startNode(c -> c
        .selfHost(host)
        .selfPortAkka(port)
        .discoveryAkkaSeed(seedList)
        .startParam(startParam));
  }

  @Override
  public ClusterNode startNode(Function<Start, Start> startConfig) {
    ClusterNodeStarter.Config config = new StartConfigMaker(startConfig).make();
    return new ClusterNodeStarter(_appContext, config).start();
  }

  ApplicationContext _appContext;
}
