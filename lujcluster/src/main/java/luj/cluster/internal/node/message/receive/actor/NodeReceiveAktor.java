package luj.cluster.internal.node.message.receive.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

/**
 * 用于接收所有外部节点消息，然后会根据注册关系派发给内部actor
 */
public class NodeReceiveAktor extends AbstractActor {

  public static Props props(ActorMessageHandleMapV2 messageHandleMap,
      ActorRef nodeSendRef, ActorRef appRootRef) {
    return Props.create(NodeReceiveAktor.class, () ->
        new NodeReceiveAktor(messageHandleMap, nodeSendRef, appRootRef));
  }

  NodeReceiveAktor(ActorMessageHandleMapV2 messageHandleMap,
      ActorRef nodeSendRef, ActorRef appRootRef) {
    _messageHandleMap = messageHandleMap;

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

  /**
   * @see luj.cluster.internal.node.message.send.actor.NodeSendAktor
   */
  private final ActorRef _nodeSendRef;

  /**
   * @see luj.cluster.internal.node.appactor.akka.root.AppRootAktor
   */
  private final ActorRef _appRootRef;
}
