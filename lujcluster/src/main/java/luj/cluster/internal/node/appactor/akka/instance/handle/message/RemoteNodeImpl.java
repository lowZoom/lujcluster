package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class RemoteNodeImpl implements ActorMessageHandler.Node {

  RemoteNodeImpl(ActorRef remoteRef) {
    _remoteRef = remoteRef;
  }

  @Override
  public String getIp() {
    return _remoteRef.path().address().host().get();
  }

  @Override
  public void sendMessage(Object msg) {
    NodeSendRemoteMsg resultMsg = new NodeSendRemoteMsg(msg.getClass().getName(), msg);
    _remoteRef.tell(resultMsg, ActorRef.noSender());
  }

  /**
   * @see luj.cluster.internal.node.member.NodeMemberAktor
   */
  private final ActorRef _remoteRef;
}
