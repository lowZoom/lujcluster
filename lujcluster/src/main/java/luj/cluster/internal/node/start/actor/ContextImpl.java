package luj.cluster.internal.node.start.actor;

import akka.actor.ActorRef;
import akka.event.LoggingAdapter;
import java.util.Map;
import luj.cluster.api.start.NodeStartListener;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;

final class ContextImpl implements NodeStartListener.Context {

  ContextImpl(ActorRef receiveRef, ActorRef sendRef, LoggingAdapter logger) {
    _receiveRef = receiveRef;
    _sendRef = sendRef;

    _logger = logger;
  }

  @Override
  public void registerMessageHandler(Map<String, ?> handlerMap) {
    _receiveRef.tell(new RegisterReceiveMsg(handlerMap), ActorRef.noSender());
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _sendRef.tell(new NodeSendStartMsg(msgKey, msg), ActorRef.noSender());
  }

  @Override
  public LoggingAdapter getLogger() {
    return _logger;
  }

  private final ActorRef _receiveRef;
  private final ActorRef _sendRef;

  private final LoggingAdapter _logger;
}
