package luj.cluster.internal.node.appactor.meta;

import java.util.Map;

final class ActorMetaMapImpl implements ActorMetaMap {

  ActorMetaMapImpl(Map<Class<?>, ActorMeta> mapImpl) {
    _mapImpl = mapImpl;
  }

  @Override
  public ActorMeta getMeta(Class<?> actorType) {
    return _mapImpl.get(actorType);
  }

  private final Map<Class<?>, ActorMeta> _mapImpl;
}
