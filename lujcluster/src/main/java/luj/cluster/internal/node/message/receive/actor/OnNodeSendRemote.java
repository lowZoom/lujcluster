package luj.cluster.internal.node.message.receive.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.message.listener.NodeMessageListenInvoker;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnNodeSendRemote implements FI.UnitApply<NodeSendRemoteMsg> {

  OnNodeSendRemote(NodeReceiveAktor actor) {
    _actor = actor;
  }

  @Override
  public void apply(NodeSendRemoteMsg i) {
    String msgKey = i.getMessageKey();
    ActorRef senderRef = _actor.sender();
    LOG.debug("[cluster]收到远程节点消息：{}（{}）", msgKey, senderRef);

    new NodeMessageListenInvoker(msgKey, i.getMessage(),
        _actor.getMsgHandleMap(), _actor.getAppRootRef(), senderRef).invoke();
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnNodeSendRemote.class);

  private final NodeReceiveAktor _actor;
}
