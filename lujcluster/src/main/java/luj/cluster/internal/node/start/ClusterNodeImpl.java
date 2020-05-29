package luj.cluster.internal.node.start;

import akka.actor.ActorRef;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class ClusterNodeImpl implements ClusterNode {

  @Override
  public void sendMessage(String msgKey, Object msg, ActorRef sender) {
    _receiveRef.tell(new NodeSendRemoteMsg(msgKey, msg), sender);
  }

  public void setReceiveRef(ActorRef receiveRef) {
    _receiveRef = receiveRef;
  }

  private ActorRef _receiveRef;
}
