package luj.cluster.internal.node.message.receive.actor;

import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.message.listener.MessageListenInvoker;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

final class OnNodeSendRemote implements FI.UnitApply<NodeSendRemoteMsg> {

  OnNodeSendRemote(NodeReceiveActor actor) {
    _actor = actor;
  }

  @Override
  public void apply(NodeSendRemoteMsg i) {
    Map<String, Object> handlerMap = _actor.getHandlerMap();
    String msgKey = i.getMessageKey();
    Object handler = handlerMap.get(msgKey);

    System.out.println("收到远程节点消息");
    System.out.println(msgKey + handler);

    MessageListenInvoker.Factory.create(
        _actor.getMessageListener(), i.getMessage(), handler).invoke();
  }

  private final NodeReceiveActor _actor;
}
