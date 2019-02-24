package luj.cluster.internal.node.message.send.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;
import luj.cluster.internal.node.message.send.message.RegisterHandleMsg;

public final class NodeSendActor extends AbstractActor {

  public static Props props() {
    return Props.create(NodeSendActor.class, () -> new NodeSendActor(new HashMap<>()));
  }

  private NodeSendActor(Map<String, ActorRef> receiveMap) {
    _receiveMap = receiveMap;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(RegisterHandleMsg.class, new OnRegisterHandle(this))
        .match(NodeSendStartMsg.class, new OnNodeSend(this))
        .build();
  }

  Map<String, ActorRef> getReceiveMap() {
    return _receiveMap;
  }

  private final Map<String, ActorRef> _receiveMap;
}
