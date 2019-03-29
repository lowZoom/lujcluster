package luj.cluster.internal.node.message.send.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.send.actor.message.NodeSendStartMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnNodeSend implements FI.UnitApply<NodeSendStartMsg> {

  OnNodeSend(NodeSendAktor actor) {
    _actor = actor;
  }

  @Override
  public void apply(NodeSendStartMsg i) {
    Map<String, ActorRef> receiveMap = _actor.getReceiveMap();
    String msgKey = i.getMessageId();

    ActorRef receiveRef = receiveMap.get(msgKey);
    if (receiveRef == null) {
      LOG.warn("未处理节点消息：{}", msgKey);
      return;
    }

    receiveRef.tell(new NodeSendRemoteMsg(msgKey, i.getMessage()), ActorRef.noSender());
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnNodeSend.class);

  private final NodeSendAktor _actor;
}
