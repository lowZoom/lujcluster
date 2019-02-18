package luj.cluster.api;

import luj.cluster.internal.session.ClusterSessionFactory;

public enum LujCluster {
  ;

  public static ClusterSession start(){
    return ClusterSessionFactory.get().create();
  }
}
