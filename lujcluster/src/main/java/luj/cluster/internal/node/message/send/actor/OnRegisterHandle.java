package luj.cluster.internal.node.message.send.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.message.send.message.RegisterHandleMsg;

final class OnRegisterHandle implements FI.UnitApply<RegisterHandleMsg> {

  OnRegisterHandle(NodeSendActor actor) {
    _actor = actor;
  }

  @Override
  public void apply(RegisterHandleMsg i) {
    Map<String, ActorRef> handleMap = _actor.getReceiveMap();
    ActorRef handleRef = i.getHandleRef();

    for (String msgId : i.getIdSet()) {
      handleMap.put(msgId, handleRef);
    }
  }

  private final NodeSendActor _actor;
}
