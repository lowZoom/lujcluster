package luj.cluster.internal.node.start;

import org.springframework.context.ApplicationContext;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(ApplicationContext appContext) {
      return new ClusterNodeStarterImpl(appContext);
    }
  }

  void start();
}
