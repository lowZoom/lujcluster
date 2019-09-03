package luj.cluster.internal.node.member;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class OnSendRemote implements FI.UnitApply<NodeSendRemoteMsg> {

  OnSendRemote(NodeMemberAktor memberAktor, ActorRef receiveRef) {
    _memberAktor = memberAktor;
    _receiveRef = receiveRef;
  }

  @Override
  public void apply(NodeSendRemoteMsg i) {
    _receiveRef.forward(i, _memberAktor.context());
  }

  private final NodeMemberAktor _memberAktor;

  private final ActorRef _receiveRef;
}
