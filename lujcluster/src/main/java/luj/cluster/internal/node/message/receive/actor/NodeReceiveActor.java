package luj.cluster.internal.node.message.receive.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

public final class NodeReceiveActor extends AbstractActor {

  public static Props props(NodeMessageListener nodeMessageListener, ActorRef nodeSendRef) {
    return Props.create(NodeReceiveActor.class, () ->
        new NodeReceiveActor(new HashMap<>(), nodeMessageListener, nodeSendRef));
  }

  private NodeReceiveActor(Map<String, Object> handlerMap,
      NodeMessageListener messageListener, ActorRef nodeSendRef) {
    _handlerMap = handlerMap;
    _messageListener = messageListener;

    _nodeSendRef = nodeSendRef;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(RegisterReceiveMsg.class, new OnRegisterReceive(this))
        .match(NodeSendRemoteMsg.class, new OnNodeSendRemote(this))
        .build();
  }

  public NodeMessageListener getMessageListener() {
    return _messageListener;
  }

  public ActorRef getNodeSendRef() {
    return _nodeSendRef;
  }

  Map<String, Object> getHandlerMap() {
    return _handlerMap;
  }

  private final Map<String, Object> _handlerMap;
  private final NodeMessageListener _messageListener;

  /**
   * @see luj.cluster.internal.node.message.send.actor.NodeSendActor
   */
  private final ActorRef _nodeSendRef;
}
