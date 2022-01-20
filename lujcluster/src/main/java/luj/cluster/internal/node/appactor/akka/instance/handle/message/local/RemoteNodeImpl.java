package luj.cluster.internal.node.appactor.akka.instance.handle.message.local;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class RemoteNodeImpl implements ActorMessageHandler.Node, NodeNewMemberListener.Node {

  @Override
  public String getIp() {
    return _remoteRef.path().address().host().get();
  }

  @Override
  public void sendMessage(Object msg) {
    _remoteRef.tell(msg, _localRef);
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _remoteRef.tell(new NodeSendRemoteMsg(msgKey, msg), _localRef);
  }

  @Override
  public String toString() {
    return _remoteRef.toString();
  }

  /**
   * @see luj.cluster.internal.node.member.actor.NodeMemberAktor
   */
  ActorRef _remoteRef;

  ActorRef _localRef;
}
