package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.ClusterNodeStarter;

final class ClusterSessionFactoryImpl implements ClusterSessionFactory {

  @Override
  public ClusterSession create() {
    ClusterNodeStarter starter = ClusterNodeStarter.Factory.create();
    starter.start();

    return null;
  }
}
