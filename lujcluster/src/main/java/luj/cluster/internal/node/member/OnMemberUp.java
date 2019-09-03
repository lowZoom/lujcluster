package luj.cluster.internal.node.member;

import akka.actor.ActorSelection;
import akka.actor.Address;
import akka.cluster.ClusterEvent;
import akka.cluster.Member;
import akka.japi.pf.FI;
import luj.cluster.api.node.NodeNewMemberListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnMemberUp implements FI.UnitApply<ClusterEvent.MemberUp> {

  OnMemberUp(NodeMemberAktor aktor, NodeNewMemberListener joinListener) {
    _aktor = aktor;
    _joinListener = joinListener;
  }

  /**
   * @see luj.cluster.internal.node.start.actor.PreStart#createMemberActor
   */
  @Override
  public void apply(ClusterEvent.MemberUp i) {
    LOG.debug("新节点加入：{}", i);

    if (_joinListener == null) {
      LOG.info("没有监听新节点事件，忽略");
      return;
    }

    Member member = i.member();
    Address addr = member.address();

    ActorSelection recvRef = _aktor.context().actorSelection(addr + "/user/start/recv");
    NodeImpl node = new NodeImpl(recvRef, _aktor.self());

    MemberContextImpl ctx = new MemberContextImpl(node);
    _joinListener.onMember(ctx);
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnMemberUp.class);

  private final NodeMemberAktor _aktor;
  private final NodeNewMemberListener _joinListener;
}
