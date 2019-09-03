package luj.cluster.internal.node.message.receive.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

public class NodeReceiveAktor extends AbstractActor {

  public static Props props(ActorMessageHandleMapV2 messageHandleMap,
      NodeMessageListener nodeMessageListener, ActorRef nodeSendRef, ActorRef appRootRef) {
    return Props.create(NodeReceiveAktor.class, () ->
        new NodeReceiveAktor(messageHandleMap, nodeMessageListener, nodeSendRef, appRootRef));
  }

  NodeReceiveAktor(ActorMessageHandleMapV2 messageHandleMap,
      NodeMessageListener messageListener, ActorRef nodeSendRef, ActorRef appRootRef) {
    _messageHandleMap = messageHandleMap;
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

  public ActorMessageHandleMapV2 getMessageHandleMap() {
    return _messageHandleMap;
  }

  private final ActorMessageHandleMapV2 _messageHandleMap;

  @Deprecated
  private final NodeMessageListener _messageListener;

  /**
   * @see luj.cluster.internal.node.message.send.actor.NodeSendAktor
   */
  private final ActorRef _nodeSendRef;

  /**
   * @see luj.cluster.internal.node.appactor.akka.root.AppRootAktor
   */
  private final ActorRef _appRootRef;
}
