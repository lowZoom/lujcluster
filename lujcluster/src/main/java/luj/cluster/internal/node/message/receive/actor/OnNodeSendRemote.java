package luj.cluster.internal.node.message.receive.actor;

import akka.japi.pf.FI;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
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
    @Deprecated
    Map<String, Object> handlerMap = ImmutableMap.of();// _actor.getHandlerMap();

    String msgKey = i.getMessageKey();
    Object handler = handlerMap.get(msgKey);

    LOG.debug("[cluster]收到远程节点消息：{}（{}）", msgKey, _actor.sender());
    NodeMessageListenInvoker.Factory.create(_actor, msgKey, i.getMessage(), handler).invoke();
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnNodeSendRemote.class);

  private final NodeReceiveAktor _actor;
}
