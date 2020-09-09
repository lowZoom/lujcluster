package luj.cluster.internal.node.member.message;

import akka.actor.ActorRef;

public class StartMemberMsg {

  public StartMemberMsg(ActorRef receiveRef) {
    _receiveRef = receiveRef;
  }

  public ActorRef getReceiveRef() {
    return _receiveRef;
  }

  private final ActorRef _receiveRef;
}
