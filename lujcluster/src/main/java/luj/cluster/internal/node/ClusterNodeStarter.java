package luj.cluster.internal.node;

import java.util.List;
import luj.cluster.api.start.NodeStartListener;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(List<NodeStartListener> startListeners) {
      return new ClusterNodeStarterImpl(startListeners);
    }
  }

  void start();
}
