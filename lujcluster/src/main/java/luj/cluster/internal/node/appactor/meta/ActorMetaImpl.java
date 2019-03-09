package luj.cluster.internal.node.appactor.meta;

import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class ActorMetaImpl implements ActorMeta {

  ActorMetaImpl(ActorMessageHandleMap actorMessageHandleMap) {
    _actorMessageHandleMap = actorMessageHandleMap;
  }

  @Override
  public ActorMessageHandleMap getMessageHandleMap() {
    return _actorMessageHandleMap;
  }

  private final ActorMessageHandleMap _actorMessageHandleMap;
}
