package luj.cluster.internal.node.message.listener;

import luj.cluster.api.message.NodeMessageListener;

public interface MessageListenInvoker {

  interface Factory {

    static MessageListenInvoker create(NodeMessageListener messageListener, Object message, Object handler) {
      return new MessageListenInvokerImpl(messageListener, message, handler);
    }
  }

  void invoke();
}
