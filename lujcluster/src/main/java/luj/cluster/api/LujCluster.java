package luj.cluster.api;

import luj.cluster.internal.session.ClusterSessionFactory;
import org.springframework.context.ApplicationContext;

public enum LujCluster {
  ;

  public static ClusterSession start(ApplicationContext appContext) {
    return ClusterSessionFactory.get(appContext).create();
  }
}
