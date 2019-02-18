package luj.cluster.internal.node;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create() {
      return new ClusterNodeStarterImpl();
    }
  }

  void start();
}
