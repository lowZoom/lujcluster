package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class ActorMetaImpl implements ActorMeta {

  ActorMetaImpl(ActorPreSstartHandler<?> preStartHandler,
      ActorMessageHandleMap actorMessageHandleMap) {
    _preStartHandler = preStartHandler;
    _actorMessageHandleMap = actorMessageHandleMap;
  }

  @Override
  public ActorPreSstartHandler<?> getPreStartHandler() {
    return _preStartHandler;
  }

  @Override
  public ActorMessageHandleMap getMessageHandleMap() {
    return _actorMessageHandleMap;
  }

  private final ActorPreSstartHandler<?> _preStartHandler;

  private final ActorMessageHandleMap _actorMessageHandleMap;
}
