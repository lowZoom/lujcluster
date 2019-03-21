package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class ActorMetaImpl implements ActorMeta {

  ActorMetaImpl(ActorPreStartHandler<?> preStartHandler,
      ActorMessageHandleMap actorMessageHandleMap) {
    _preStartHandler = preStartHandler;
    _actorMessageHandleMap = actorMessageHandleMap;
  }

  @Override
  public ActorPreStartHandler<?> getPreStartHandler() {
    return _preStartHandler;
  }

  @Override
  public ActorMessageHandleMap getMessageHandleMap() {
    return _actorMessageHandleMap;
  }

  private final ActorPreStartHandler<?> _preStartHandler;

  private final ActorMessageHandleMap _actorMessageHandleMap;
}
