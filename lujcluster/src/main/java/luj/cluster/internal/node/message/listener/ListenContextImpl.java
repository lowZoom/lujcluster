package luj.cluster.internal.node.message.listener;

import akka.event.LoggingAdapter;
import luj.cluster.api.message.NodeMessageListener.Context;
import luj.cluster.api.message.NodeMessageListener.Message;

final class ListenContextImpl implements Context, Message {

  ListenContextImpl(Object message, Object handler) {
    _message = message;
    _handler = handler;
  }

  @Override
  public Message getMessage() {
    return this;
  }

  @Override
  public LoggingAdapter getLogger() {
    return null;
  }

  @Override
  public String getId() {
    return null;
  }

  @Override
  public Object getPayload() {
    return _message;
  }

  @Override
  public <T> T getHandler() {
    return (T) _handler;
  }

  private final Object _message;

  private final Object _handler;
}
