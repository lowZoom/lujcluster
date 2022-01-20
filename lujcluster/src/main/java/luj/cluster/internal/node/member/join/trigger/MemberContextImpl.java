package luj.cluster.internal.node.member.join.trigger;

import luj.cluster.api.node.NodeNewMemberListener;

final class MemberContextImpl implements NodeNewMemberListener.Context {

  @Override
  public NodeNewMemberListener.Node getSelfNode() {
    return _selfNode;
  }

  @Override
  public NodeNewMemberListener.NodeRemote getMemberNode() {
    return _memberNode;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationBean() {
    return (T) _applicationBean;
  }

  NodeNewMemberListener.Node _selfNode;
  NodeNewMemberListener.NodeRemote _memberNode;

  Object _applicationBean;
}
