package luj.cluster.internal.node.member.actor.message;

import akka.actor.ActorRef;
import luj.cluster.internal.node.start.ClusterNodeStarter;

public class StartMemberMsg {

  public StartMemberMsg(ActorRef receiveRef, boolean clusterEnabled,
      ClusterNodeStarter.Config nodeConfig) {
    _receiveRef = receiveRef;
    _clusterEnabled = clusterEnabled;
    _nodeConfig = nodeConfig;
  }

  public ActorRef getReceiveRef() {
    return _receiveRef;
  }

  public boolean isClusterEnabled() {
    return _clusterEnabled;
  }

  public ClusterNodeStarter.Config getNodeConfig() {
    return _nodeConfig;
  }

  private final ActorRef _receiveRef;

  private final boolean _clusterEnabled;
  private final ClusterNodeStarter.Config _nodeConfig;
}
