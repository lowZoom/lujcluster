package luj.cluster.internal.node.appactor.akka.instance.handle.message.local;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.member.actor.message.LeaveAndShutdownMsg;

final class LocalNodeImpl implements ActorMessageHandler.NodeLocal {

  /**
   * @see luj.cluster.internal.node.member.actor.OnLeaveAndShutdown#apply
   */
  @Override
  public void shutdown() {
    _clusterMemberRef.tell(LeaveAndShutdownMsg.INSTANCE, _selfRef);
  }

  ActorRef _selfRef;

  /**
   * @see luj.cluster.internal.node.member.actor.NodeMemberAktor
   */
  ActorRef _clusterMemberRef;
}
