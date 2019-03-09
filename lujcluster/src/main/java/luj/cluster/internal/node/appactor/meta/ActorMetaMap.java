package luj.cluster.internal.node.appactor.meta;

import luj.cluster.internal.session.inject.ClusterBeanCollector;

public interface ActorMetaMap {

  interface Factory {

    static ActorMetaMap create(ClusterBeanCollector.Result beanCollect) {
      return ActorMetaMapFactoryImpl.SINGLETON.create(beanCollect);
    }
  }

  ActorMeta getMeta(Class<?> actorType);
}
