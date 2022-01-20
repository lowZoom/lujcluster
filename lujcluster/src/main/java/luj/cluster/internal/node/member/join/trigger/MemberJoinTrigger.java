package luj.cluster.internal.node.member.join.trigger;

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
    MemberContextImpl ctx = new MemberContextImpl();
    ctx._selfNode = makeSelfNode();
    ctx._memberNode = makeRemoteNode();
    ctx._applicationBean = _applicationBean;

    try {
      _joinListener.onMember(ctx);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private NodeSelfImpl makeSelfNode() {
    NodeSelfImpl node = new NodeSelfImpl();
    node._receiveRef = _aktor.getReceiveRef();
    node._memberRef = _aktor.self();
    return node;
  }

  private NodeMemberImpl makeRemoteNode() {
    Member member = _event.member();
    Address addr = member.address();
    ActorSelection recvRef = _aktor.context().actorSelection(addr + "/user/start/recv");

    NodeMemberImpl node = new NodeMemberImpl();
    node._remoteRef = recvRef;
    node._memberRef = _aktor.self();

    return node;
  }

  private final ClusterEvent.MemberUp _event;

  private final NodeMemberAktor _aktor;
  private final NodeNewMemberListener _joinListener;

  private final Object _applicationBean;
}
