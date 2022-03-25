package luj.cluster.internal.node.appactor.akka.instance.handle.message.remote;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.internal.node.member.actor.message.MemberSendRpcMsg;

final class RemoteNodeImpl implements ActorMessageHandler.Node, NodeNewMemberListener.Node {

  @Override
  public String getIp() {
    return _targetHost;
  }

  @Override
  public void sendMessage(Object msg) {
    throw new UnsupportedOperationException("sendMessage已废弃");
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _memberRef.tell(new MemberSendRpcMsg(
        msgKey, msg, _targetHost, _targetPort), ActorRef.noSender());
  }

  String _targetHost;
  int _targetPort;

  ActorRef _memberRef;
}
