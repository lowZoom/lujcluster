package luj.cluster.internal.node.actor;

import java.util.List;
import luj.cluster.api.start.NodeStartListener;

final class PreStart {

  PreStart(List<NodeStartListener> startListeners, NodeStartListener.Context startContext) {
    _startListeners = startListeners;
    _startContext = startContext;
  }

  void run() {
    System.out.print("[");
    System.out.print(Thread.currentThread().getName());
    System.out.println("] akka启动");

    for (NodeStartListener listener : _startListeners) {
      listener.onStart(_startContext);
    }
  }

  private final List<NodeStartListener> _startListeners;

  private final NodeStartListener.Context _startContext;
}
