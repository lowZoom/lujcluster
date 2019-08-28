package luj.cluster.internal.node.member;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class NodeImpl implements NodeNewMemberListener.Node {

  NodeImpl(ActorSelection remoteRef) {
    _remoteRef = remoteRef;
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _remoteRef.tell(new NodeSendRemoteMsg(msgKey, msg), ActorRef.noSender());
  }

  private final ActorSelection _remoteRef;
}
