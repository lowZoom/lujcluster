package luj.cluster.internal.node.message.send.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;

final class OnNodeSend implements FI.UnitApply<NodeSendStartMsg> {

  OnNodeSend(NodeSendActor actor) {
    _actor = actor;
  }

  @Override
  public void apply(NodeSendStartMsg i) {
    Map<String, ActorRef> receiveMap = _actor.getReceiveMap();
    String msgKey = i.getMessageId();

    ActorRef receiveRef = receiveMap.get(msgKey);
    if (receiveRef == null) {
      System.out.println("无人处理的消息：" + msgKey);
      return;
    }

    receiveRef.tell(new NodeSendRemoteMsg(msgKey, i.getMessage()), ActorRef.noSender());
  }

  private final NodeSendActor _actor;
}
