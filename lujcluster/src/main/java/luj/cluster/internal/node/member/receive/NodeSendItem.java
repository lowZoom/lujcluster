package luj.cluster.internal.node.member.receive;

import akka.actor.ActorRef;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

public class NodeSendItem {

  public NodeSendItem(NodeSendRemoteMsg message, ActorRef sender) {
    _message = message;
    _sender = sender;
  }

  public NodeSendRemoteMsg getMessage() {
    return _message;
  }

  public ActorRef getSender() {
    return _sender;
  }

  private final NodeSendRemoteMsg _message;

  private final ActorRef _sender;
}
