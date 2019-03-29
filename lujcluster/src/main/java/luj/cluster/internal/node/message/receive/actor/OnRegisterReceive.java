package luj.cluster.internal.node.message.receive.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.send.actor.message.RegisterHandleMsg;

final class OnRegisterReceive implements FI.UnitApply<RegisterReceiveMsg> {

  OnRegisterReceive(NodeReceiveAktor actor) {
    _actor = actor;
  }

  @Override
  public void apply(RegisterReceiveMsg i) {
    Map<String, Object> handlerMap = _actor.getHandlerMap();
    Map<String, ?> registerMap = i.getRegisterMap();
    handlerMap.putAll(registerMap);

    registerToSend(registerMap);
  }

  /**
   * @see luj.cluster.internal.node.message.send.actor.OnRegisterHandle#apply
   */
  private void registerToSend(Map<String, ?> registerMap) {
    ActorRef selfRef = _actor.getSelf();
    RegisterHandleMsg msg = new RegisterHandleMsg(registerMap.keySet(), selfRef);

    _actor.getNodeSendRef().tell(msg, selfRef);
  }

  private final NodeReceiveAktor _actor;
}
