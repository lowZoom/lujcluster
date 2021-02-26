package luj.cluster.internal.node.member.message;

import akka.actor.ActorRef;

public class StartMemberMsg {

  public StartMemberMsg(ActorRef receiveRef, boolean clusterEnabled) {
    _receiveRef = receiveRef;
    _clusterEnabled = clusterEnabled;
  }

  public ActorRef getReceiveRef() {
    return _receiveRef;
  }

  public boolean isClusterEnabled() {
    return _clusterEnabled;
  }

  private final ActorRef _receiveRef;

  private final boolean _clusterEnabled;
}
