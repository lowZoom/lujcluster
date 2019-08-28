package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;

@Deprecated
final class NodeMessageListenInvokerImpl implements NodeMessageListenInvoker {

  NodeMessageListenInvokerImpl(NodeMessageListener messageListener, Object message,
      Object handler, NodeReceiveAktor receiveAktor) {
    _messageListener = messageListener;

    _message = message;
    _handler = handler;

    _receiveAktor = receiveAktor;
  }

  @Override
  public void invoke() {
    ActorRef sendRef = _receiveAktor.getNodeSendRef();
    ActorRef appRef = _receiveAktor.getAppRootRef();

    ListenContextImpl ctx = new ListenContextImpl(_message, _handler, sendRef, appRef);
    _messageListener.onMessage(ctx);
  }

  private final NodeReceiveAktor _receiveAktor;

  private final Object _message;
  private final Object _handler;

  private final NodeMessageListener _messageListener;
}
