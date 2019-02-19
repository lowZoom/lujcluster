package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import org.springframework.context.ApplicationContext;

public interface ClusterSessionFactory {

  static ClusterSessionFactory get(ApplicationContext appContext) {
    return new ClusterSessionFactoryImpl(appContext);
  }

  ClusterSession create();
}
