package luj.cluster.internal.node.member;

import akka.cluster.ClusterEvent;
import akka.japi.pf.FI;

final class OnMemberUp implements FI.UnitApply<ClusterEvent.MemberUp> {

  OnMemberUp(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(ClusterEvent.MemberUp i) {
    System.out.println(i);
  }

  private final NodeMemberAktor _aktor;
}
