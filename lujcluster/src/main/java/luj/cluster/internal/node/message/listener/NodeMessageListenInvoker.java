package luj.cluster.internal.node.message.listener;

import luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor;

public interface NodeMessageListenInvoker {

  interface Factory {

    static NodeMessageListenInvoker create(NodeReceiveAktor recvActor,
        String msgKey, Object msg, Object handler) {
      return new NodeMessageListenInvokerImpl(recvActor.getMessageListener(), msg, handler, recvActor);
//      return new NodeMessageListenInvokerImplV2(msgKey, msg, recvActor., recvActor);
    }
  }

  void invoke();
}
