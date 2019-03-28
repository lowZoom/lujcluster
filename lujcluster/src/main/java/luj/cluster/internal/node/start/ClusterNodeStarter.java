package luj.cluster.internal.node.start;

import org.springframework.context.ApplicationContext;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(ApplicationContext appContext, Object startParam) {
      return new ClusterNodeStarterImpl(appContext, "192.168.0.128:2552", startParam);
    }
  }

  void start();
}
