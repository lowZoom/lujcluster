package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface AppPrestartHandleInvoker {

  interface Factory {

    static AppPrestartHandleInvoker create(AppAktor aktor) {
      return new AppPrestartHandleInvokerImpl(aktor);
    }
  }

  void invoke();
}
