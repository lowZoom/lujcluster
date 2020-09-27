package luj.cluster.internal.node.member.actor;

import akka.actor.ActorContext;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.japi.pf.FI;
import luj.cluster.internal.node.member.message.StartMemberMsg;

final class OnStartMember implements FI.UnitApply<StartMemberMsg> {

  OnStartMember(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(StartMemberMsg i) {
    _aktor.setReceiveRef(i.getReceiveRef());
    ActorContext context = _aktor.context();

    Cluster cluster = Cluster.get(context.system());
    _aktor.setCluster(cluster);

    cluster.subscribe(_aktor.self(),
        ClusterEvent.initialStateAsEvents(), ClusterEvent.MemberUp.class);
  }

  private final NodeMemberAktor _aktor;
}
