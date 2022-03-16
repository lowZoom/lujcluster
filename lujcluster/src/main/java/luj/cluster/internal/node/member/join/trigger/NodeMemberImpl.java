package luj.cluster.internal.node.member.join.trigger;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import com.google.common.collect.ImmutableSet;
import java.util.Set;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.api.node.member.NodeType;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class NodeMemberImpl implements NodeNewMemberListener.NodeRemote {

  @Override
  public NodeType getType() {
    return NodeType.AKKA;
  }

  @Override
  public String getName() {
    return _remoteRef.pathString();
  }

  @Override
  public Set<String> getTags() {
    return ImmutableSet.of();
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _remoteRef.tell(new NodeSendRemoteMsg(msgKey, msg), _memberRef);
  }

  /**
   * @see luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor
   */
  ActorSelection _remoteRef;

  ActorRef _memberRef;
}
