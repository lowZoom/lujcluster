package luj.cluster.internal.session.inject;

import java.util.List;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.api.start.NodeStartListener;
import org.springframework.context.ApplicationContext;

public interface ClusterBeanCollector {

  interface Factory {

    static ClusterBeanCollector create(ApplicationContext appContext) {
      return new ClusterBeanCollectorImpl(appContext);
    }
  }

  interface Result {

    List<NodeStartListener> getStartListeners();

    NodeMessageListener getMessageListener();
  }

  Result collect();
}
