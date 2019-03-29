package luj.cluster.internal.node.message.receive.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.send.actor.NodeSendAktor;

public class NodeReceiveAktor extends AbstractActor {

  public static Props props(NodeMessageListener nodeMessageListener,
      ActorRef nodeSendRef, ActorRef appRootRef) {
    return Props.create(NodeReceiveAktor.class, () ->
        new NodeReceiveAktor(new HashMap<>(), nodeMessageListener, nodeSendRef, appRootRef));
  }

  NodeReceiveAktor(Map<String, Object> handlerMap,
      NodeMessageListener messageListener, ActorRef nodeSendRef, ActorRef appRootRef) {
    _handlerMap = handlerMap;
    _messageListener = messageListener;

    _nodeSendRef = nodeSendRef;
    _appRootRef = appRootRef;
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

  public ActorRef getAppRootRef() {
    return _appRootRef;
  }

  Map<String, Object> getHandlerMap() {
    return _handlerMap;
  }

  private final Map<String, Object> _handlerMap;
  private final NodeMessageListener _messageListener;

  /**
   * @see NodeSendAktor
   */
  private final ActorRef _nodeSendRef;

  private final ActorRef _appRootRef;
}
