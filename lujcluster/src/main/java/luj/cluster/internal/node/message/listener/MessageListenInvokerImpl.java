package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import luj.cluster.api.message.NodeMessageListener;

final class MessageListenInvokerImpl implements MessageListenInvoker {

  MessageListenInvokerImpl(NodeMessageListener messageListener,
      Object message, Object handler, ActorRef sendRef) {
    _messageListener = messageListener;

    _message = message;
    _handler = handler;

    _sendRef = sendRef;
  }

  @Override
  public void invoke() {
    ListenContextImpl ctx = new ListenContextImpl(_message, _handler, _sendRef);
    _messageListener.onMessage(ctx);
  }

  private final NodeMessageListener _messageListener;

  private final Object _message;
  private final Object _handler;

  private final ActorRef _sendRef;
}
