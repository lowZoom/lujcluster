package luj.cluster.internal.node.message.listener;

import luj.cluster.internal.node.message.receive.actor.NodeReceiveActor;

public interface MessageListenInvoker {

  interface Factory {

    static MessageListenInvoker create(NodeReceiveActor recvActor, Object message, Object handler) {
      return new MessageListenInvokerImpl(recvActor.getMessageListener(),
          message, handler, recvActor.getNodeSendRef());
    }
  }

  void invoke();
}
