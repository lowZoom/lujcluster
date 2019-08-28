package luj.cluster.internal.node.member;

import luj.cluster.api.node.NodeNewMemberListener;

final class MemberContextImpl implements NodeNewMemberListener.Context {

  MemberContextImpl(NodeNewMemberListener.Node node) {
    _node = node;
  }

  @Override
  public NodeNewMemberListener.Node getMemberNode() {
    return _node;
  }

  private final NodeNewMemberListener.Node _node;
}
