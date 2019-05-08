package luj.cluster.internal.node.start;

import org.springframework.context.ApplicationContext;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(ApplicationContext appContext,
        String seedAddr, Object startParam) {
      return new ClusterNodeStarterImpl(appContext, seedAddr.split(":")[0], seedAddr, startParam);
    }
  }

  void start();
}
