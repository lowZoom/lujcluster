package luj.cluster.internal.node.message.receive.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.Map;
import luj.cluster.api.node.message.NodeMessageSerializer;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemote2Msg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

/**
 * 用于接收所有外部节点消息，然后会根据注册关系派发给内部actor
 */
public class NodeReceiveAktor extends AbstractActor {

  public static Props props(ActorMessageHandleMapV2 msgHandleMap,
      Map<String, NodeMessageSerializer<?>> msgCodecMap, ActorRef nodeSendRef,
      ActorRef appRootRef) {
    return Props.create(NodeReceiveAktor.class, () ->
        new NodeReceiveAktor(msgHandleMap, msgCodecMap, nodeSendRef, appRootRef));
  }

  NodeReceiveAktor(ActorMessageHandleMapV2 msgHandleMap,
      Map<String, NodeMessageSerializer<?>> msgCodecMap, ActorRef nodeSendRef,
      ActorRef appRootRef) {
    _msgHandleMap = msgHandleMap;
    _msgCodecMap = msgCodecMap;

    _nodeSendRef = nodeSendRef;
    _appRootRef = appRootRef;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(RegisterReceiveMsg.class, new OnRegisterReceive(this))
        .match(NodeSendRemoteMsg.class, new OnNodeSendRemote(this))
        .match(NodeSendRemote2Msg.class, new OnNodeSendRemote2(this))
        .build();
  }

  public ActorRef getNodeSendRef() {
    return _nodeSendRef;
  }

  public ActorRef getAppRootRef() {
    return _appRootRef;
  }

  public ActorMessageHandleMapV2 getMsgHandleMap() {
    return _msgHandleMap;
  }

  public Map<String, NodeMessageSerializer<?>> getMsgCodecMap() {
    return _msgCodecMap;
  }

  private final ActorMessageHandleMapV2 _msgHandleMap;
  private final Map<String, NodeMessageSerializer<?>> _msgCodecMap;

  /**
   * @see luj.cluster.internal.node.message.send.actor.NodeSendAktor
   */
  private final ActorRef _nodeSendRef;

  /**
   * @see luj.cluster.internal.node.appactor.akka.root.AppRootAktor
   */
  private final ActorRef _appRootRef;
}
