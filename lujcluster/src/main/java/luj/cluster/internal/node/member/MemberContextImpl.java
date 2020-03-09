package luj.cluster.internal.node.member;

import luj.cluster.api.node.NodeNewMemberListener;

final class MemberContextImpl implements NodeNewMemberListener.Context {

  MemberContextImpl(NodeNewMemberListener.Node node, Object applicationBean) {
    _node = node;
    _applicationBean = applicationBean;
  }

  @Override
  public NodeNewMemberListener.Node getMemberNode() {
    return _node;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getApplicationBean() {
    return (T) _applicationBean;
  }

  private final NodeNewMemberListener.Node _node;

  private final Object _applicationBean;
}
