package luj.cluster.internal.node.start.config;

import java.util.function.Function;
import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.start.ClusterNodeStarter;

public class StartConfigMaker {

  public StartConfigMaker(Function<ClusterSession.Start, ClusterSession.Start> builder) {
    _builder = builder;
  }

  public ClusterNodeStarter.Config make() {
    ConfigImpl config = new ConfigImpl();
    _builder.apply(config);

    return config;
  }

  private final Function<ClusterSession.Start, ClusterSession.Start> _builder;
}
