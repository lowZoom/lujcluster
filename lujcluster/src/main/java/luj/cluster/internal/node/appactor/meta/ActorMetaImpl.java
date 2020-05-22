package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class ActorMetaImpl implements ActorMeta {

  @Override
  public ActorPreStartHandler<?> getPreStartHandler() {
    return _preStartHandler;
  }

  @Override
  public ActorPostStopHandler<?> getPostStopHandler() {
    return _postStopHandler;
  }

  @Override
  public ActorMessageHandleMap getMessageHandleMap() {
    return _actorMessageHandleMap;
  }

  ActorPreStartHandler<?> _preStartHandler;
  ActorPostStopHandler<?> _postStopHandler;

  ActorMessageHandleMap _actorMessageHandleMap;
}
