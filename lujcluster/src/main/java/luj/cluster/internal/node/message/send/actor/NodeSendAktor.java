package luj.cluster.internal.node.message.send.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.internal.node.message.send.actor.message.NodeSendStartMsg;
import luj.cluster.internal.node.message.send.actor.message.RegisterHandleMsg;

public class NodeSendAktor extends AbstractActor {

  public static Props props() {
    return Props.create(NodeSendAktor.class, () -> new NodeSendAktor(new HashMap<>()));
  }

  public NodeSendAktor(Map<String, ActorRef> receiveMap) {
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
