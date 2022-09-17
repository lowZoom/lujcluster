package luj.cluster.internal.node.member.actor;

import akka.cluster.Cluster;
import akka.japi.pf.FI;
import luj.cluster.internal.node.member.actor.message.LeaveAndShutdownMsg;
import luj.cluster.internal.node.shutdown.CoordShutdownRunner;

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

    CoordShutdownRunner.get(_aktor).run();
  }

  private final NodeMemberAktor _aktor;
}
