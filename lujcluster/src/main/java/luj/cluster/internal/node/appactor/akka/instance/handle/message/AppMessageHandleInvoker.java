package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface AppMessageHandleInvoker {

  interface Factory {

    static AppMessageHandleInvoker create(AppAktor appAktor, Object msg) {
      return new AppMessageHandleInvokerImpl(appAktor, msg);
    }
  }

  void invoke();
}
