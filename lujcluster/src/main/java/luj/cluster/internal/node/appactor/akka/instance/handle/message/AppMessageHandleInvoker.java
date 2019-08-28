package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import akka.actor.Address;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface AppMessageHandleInvoker {

  interface Factory {

    static AppMessageHandleInvoker create(AppAktor appAktor, Object msg, ActorRef senderRef) {
      Address addr = senderRef.path().address();
      RemoteNodeImpl remoteNode = new RemoteNodeImpl(addr.host().get());

      return new AppMessageHandleInvokerImpl(appAktor, msg, remoteNode);
    }
  }

  void invoke();
}
