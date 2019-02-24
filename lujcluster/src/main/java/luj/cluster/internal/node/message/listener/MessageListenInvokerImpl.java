package luj.cluster.internal.node.message.listener;

import luj.cluster.api.message.NodeMessageListener;

final class MessageListenInvokerImpl implements MessageListenInvoker {

  MessageListenInvokerImpl(NodeMessageListener messageListener, Object message, Object handler) {
    _messageListener = messageListener;

    _message = message;
    _handler = handler;
  }

  @Override
  public void invoke() {
    ListenContextImpl ctx = new ListenContextImpl(_message, _handler);
    _messageListener.onMessage(ctx);
  }

  private final NodeMessageListener _messageListener;

  private final Object _message;
  private final Object _handler;
}
