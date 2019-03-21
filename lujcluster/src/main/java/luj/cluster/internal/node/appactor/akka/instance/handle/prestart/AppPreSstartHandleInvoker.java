package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface AppPreSstartHandleInvoker {

  interface Factory {

    static AppPreSstartHandleInvoker create(AppAktor aktor) {
      return new AppPreSstartHandleInvokerImpl(aktor);
    }
  }

  void invoke();
}
