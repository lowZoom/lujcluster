package luj.cluster.internal.node.member.actor;

import akka.actor.CoordinatedShutdown;
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
    if (cluster != null) {
      cluster.leave(cluster.selfAddress());
      return;
    }

    CoordinatedShutdown.get(_aktor.context().system()).runAll();
  }

  private final NodeMemberAktor _aktor;
}
