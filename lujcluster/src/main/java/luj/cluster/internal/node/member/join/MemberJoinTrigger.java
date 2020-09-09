package luj.cluster.internal.node.member.join;

import akka.actor.ActorSelection;
import akka.actor.Address;
import akka.cluster.ClusterEvent;
import akka.cluster.Member;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.member.actor.NodeMemberAktor;

public class MemberJoinTrigger {

  public MemberJoinTrigger(ClusterEvent.MemberUp event,
      NodeMemberAktor aktor, NodeNewMemberListener joinListener, Object applicationBean) {
    _event = event;
    _aktor = aktor;
    _joinListener = joinListener;
    _applicationBean = applicationBean;
  }

  public void trigger() {
    Member member = _event.member();
    Address addr = member.address();

    ActorSelection recvRef = _aktor.context().actorSelection(addr + "/user/start/recv");
    NodeImpl node = new NodeImpl(recvRef, _aktor.self());

    MemberContextImpl ctx = new MemberContextImpl(node, _applicationBean);
    try {
      _joinListener.onMember(ctx);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final ClusterEvent.MemberUp _event;

  private final NodeMemberAktor _aktor;
  private final NodeNewMemberListener _joinListener;

  private final Object _applicationBean;
}
