package luj.cluster.internal.node.member.join.trigger2;

import akka.actor.ActorRef;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class NodeSelfImpl implements NodeNewMemberListener.Node {

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _receiveRef.tell(new NodeSendRemoteMsg(msgKey, msg), _memberRef);
  }

  /**
   * @see luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor
   */
  ActorRef _receiveRef;

  ActorRef _memberRef;
}
