package luj.cluster.internal.node.appactor.meta;

import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class MessageHandleMapImpl implements ActorMessageHandleMap {

  MessageHandleMapImpl(Map<Class<?>, ActorMessageHandler<?, ?>> handlerMap) {
    _handlerMap = handlerMap;
  }

  @Override
  public ActorMessageHandler<?, ?> getHandler(Class<?> msgType) {
    return _handlerMap.get(msgType);
  }

  private final Map<Class<?>, ActorMessageHandler<?, ?>> _handlerMap;
}
