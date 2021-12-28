package luj.cluster.internal.node.member.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Queue;
import luj.cluster.internal.node.member.receive.NodeSendItem;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

/**
 * 收到远程节点发的消息
 */
final class OnNodeSendRemote implements FI.UnitApply<NodeSendRemoteMsg> {

  OnNodeSendRemote(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(NodeSendRemoteMsg i) {
    ActorRef receiveRef = _aktor.getReceiveRef();
    ActorRef sender = _aktor.sender();

    if (receiveRef == null) {
      Queue<NodeSendItem> queue = _aktor.getReceiveQueue();
      NodeSendItem item = new NodeSendItem(i, sender);

      queue.offer(item);
      return;
    }

    receiveRef.tell(i, sender);
  }

  private final NodeMemberAktor _aktor;
}
