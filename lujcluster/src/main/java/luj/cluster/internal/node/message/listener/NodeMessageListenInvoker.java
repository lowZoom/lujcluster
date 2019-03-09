package luj.cluster.internal.node.message.listener;

import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;

public interface NodeMessageListenInvoker {

  interface Factory {

    static NodeMessageListenInvoker create(NodeReceiveAktor recvActor, Object msg, Object handler) {
      return new NodeMessageListenInvokerImpl(recvActor.getMessageListener(), msg, handler, recvActor);
    }
  }

  void invoke();
}
