package luj.cluster.internal.node.start;

import java.util.List;
import org.springframework.context.ApplicationContext;

public interface ClusterNodeStarter {

  interface Factory {

    static ClusterNodeStarter create(ApplicationContext appContext, String host, int port,
        List<String> seedList, Object startParam) {
      return new ClusterNodeStarterImpl(appContext, host, port, seedList, startParam);
    }
  }

  void start();
}
