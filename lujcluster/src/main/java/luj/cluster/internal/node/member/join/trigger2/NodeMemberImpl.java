package luj.cluster.internal.node.member.join.trigger2;

import akka.actor.ActorRef;
import java.util.Set;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.api.node.NodeType;
import luj.cluster.internal.node.member.message.MemberSendRpcMsg;

final class NodeMemberImpl implements NodeNewMemberListener.NodeRemote {

  @Override
  public NodeType getType() {
    return NodeType.GRPC;
  }

  @Override
  public Set<String> getTags() {
    return _tags;
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _memberRef.tell(new MemberSendRpcMsg(
        msgKey, msg, _memberHost, _memberPort), ActorRef.noSender());
  }

  Set<String> _tags;
  ActorRef _memberRef;

  String _memberHost;
  int _memberPort;
}
