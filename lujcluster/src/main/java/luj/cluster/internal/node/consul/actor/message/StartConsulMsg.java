package luj.cluster.internal.node.consul.actor.message;

import luj.cluster.internal.node.start.ClusterNodeStarter;

public class StartConsulMsg {

  public StartConsulMsg(ClusterNodeStarter.Config nodeConfig) {
    _nodeConfig = nodeConfig;
  }

  public ClusterNodeStarter.Config getNodeConfig() {
    return _nodeConfig;
  }

  private final ClusterNodeStarter.Config _nodeConfig;
}
