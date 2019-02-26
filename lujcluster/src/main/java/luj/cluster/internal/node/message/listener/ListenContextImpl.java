package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import akka.event.LoggingAdapter;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;
import org.omg.CORBA.NO_IMPLEMENT;

final class ListenContextImpl implements NodeMessageListener.Context, NodeMessageListener.Message {

  ListenContextImpl(Object message, Object handler, ActorRef sendRef) {
    _message = message;
    _handler = handler;

    _sendRef = sendRef;
  }

  @Override
  public NodeMessageListener.Message getMessage() {
    return this;
  }

  @Override
  public LoggingAdapter getLogger() {
    throw new NO_IMPLEMENT("getLogger尚未实现");
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _sendRef.tell(new NodeSendStartMsg(msgKey, msg), ActorRef.noSender());
  }

  @Override
  public String getId() {
    return null;
  }

  @Override
  public Object getPayload() {
    return _message;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getHandler() {
    return (T) _handler;
  }

  private final Object _message;
  private final Object _handler;

  private final ActorRef _sendRef;
}
