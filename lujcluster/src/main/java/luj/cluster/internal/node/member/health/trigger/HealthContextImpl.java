package luj.cluster.internal.node.member.health.trigger;

import luj.cluster.api.node.member.NodeMemberHealthListener;

final class HealthContextImpl implements NodeMemberHealthListener.Context {

  @Override
  public NodeMemberHealthListener.LocalNode getSelfNode() {
    return _selfNode;
  }

  @Override
  public NodeMemberHealthListener.RemoteNode getMemberNode() {
    return _memberNode;
  }

  NodeLocalImpl _selfNode;

  NodeRemoteImpl _memberNode;
}
