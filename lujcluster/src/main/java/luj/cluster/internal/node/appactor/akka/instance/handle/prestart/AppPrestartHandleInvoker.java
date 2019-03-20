package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

public interface AppPrestartHandleInvoker {

  interface Factory {

    static AppPrestartHandleInvoker create() {

    }
  }

  void invoke();
}
