package luj.cluster.internal.node.start;

import org.springframework.context.ApplicationContext;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(ApplicationContext appContext, String host, int port,
        String seedAddr, Object startParam) {
      return new ClusterNodeStarterImpl(appContext, host, port, seedAddr, startParam);
    }
  }

  void start();
}
