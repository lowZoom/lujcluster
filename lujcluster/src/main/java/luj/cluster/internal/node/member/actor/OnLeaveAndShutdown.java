package luj.cluster.internal.node.member.actor;

import akka.cluster.Cluster;
import akka.japi.pf.FI;
import luj.cluster.internal.node.member.actor.message.LeaveAndShutdownMsg;

final class OnLeaveAndShutdown implements FI.UnitApply<LeaveAndShutdownMsg> {

  OnLeaveAndShutdown(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(LeaveAndShutdownMsg i) {
    Cluster cluster = _aktor.getCluster();
    cluster.leave(cluster.selfAddress());
  }

  private final NodeMemberAktor _aktor;
}
