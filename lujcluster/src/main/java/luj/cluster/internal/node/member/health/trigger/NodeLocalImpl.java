package luj.cluster.internal.node.member.health.trigger;

import akka.actor.ActorRef;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class NodeLocalImpl implements NodeMemberHealthListener.LocalNode {

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
