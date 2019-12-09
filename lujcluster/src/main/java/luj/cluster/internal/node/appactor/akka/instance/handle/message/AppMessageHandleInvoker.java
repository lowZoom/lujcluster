package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface AppMessageHandleInvoker {

  interface Factory {

    static AppMessageHandleInvoker create(AppAktor appAktor, Object msg, ActorRef senderRef) {
      RemoteNodeImpl remoteNode = new RemoteNodeImpl(senderRef, appAktor.self());
      return new AppMessageHandleInvokerImpl(appAktor, msg, remoteNode);
    }
  }

  void invoke();
}
