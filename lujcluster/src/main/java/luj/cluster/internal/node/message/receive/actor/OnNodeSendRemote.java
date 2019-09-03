package luj.cluster.internal.node.message.receive.actor;

import akka.japi.pf.FI;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import luj.cluster.internal.node.message.listener.NodeMessageListenInvoker;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class OnNodeSendRemote implements FI.UnitApply<NodeSendRemoteMsg> {

  OnNodeSendRemote(NodeReceiveAktor actor) {
    _actor = actor;
  }

  @Override
  public void apply(NodeSendRemoteMsg i) {
    Map<String, Object> handlerMap = ImmutableMap.of();// _actor.getHandlerMap();
    String msgKey = i.getMessageKey();
    Object handler = handlerMap.get(msgKey);

    System.out.println("收到远程节点消息");
    System.out.println(msgKey + handler);

    NodeMessageListenInvoker.Factory.create(_actor, msgKey, i.getMessage(), handler).invoke();
  }

  private final NodeReceiveAktor _actor;
}
