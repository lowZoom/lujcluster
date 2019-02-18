package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;

public interface ClusterSessionFactory {

  static ClusterSessionFactory get() {
    return new ClusterSessionFactoryImpl();
  }

  ClusterSession create();
}
