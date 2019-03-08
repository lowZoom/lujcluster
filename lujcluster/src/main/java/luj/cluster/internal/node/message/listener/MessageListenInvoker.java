package luj.cluster.internal.node.message.listener;

import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;

public interface MessageListenInvoker {

  interface Factory {

    static MessageListenInvoker create(NodeReceiveAktor recvActor, Object msg, Object handler) {
      return new MessageListenInvokerImpl(recvActor.getMessageListener(), msg, handler, recvActor);
    }
  }

  void invoke();
}
