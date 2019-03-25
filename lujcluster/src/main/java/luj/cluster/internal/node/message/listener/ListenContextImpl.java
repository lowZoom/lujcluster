package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import akka.event.LoggingAdapter;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;
import org.omg.CORBA.NO_IMPLEMENT;

final class ListenContextImpl implements NodeMessageListener.Context, NodeMessageListener.Message {

  ListenContextImpl(Object message, Object handler, ActorRef sendRef, ActorRef appRootRef) {
    _message = message;
    _handler = handler;

    _sendRef = sendRef;
    _appRootRef = appRootRef;
  }

  @Override
  public NodeMessageListener.Message getMessage() {
    return this;
  }

  @Override
  public <T> T getApplicationBean() {
    throw new NO_IMPLEMENT("getApplicationBean尚未实现");
  }

  @Override
  public NodeMessageListener.Actor getApplicationActor(Class<?> actorType) {
    return new ListenActorImpl(actorType, _appRootRef);
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
  private final ActorRef _appRootRef;
}
