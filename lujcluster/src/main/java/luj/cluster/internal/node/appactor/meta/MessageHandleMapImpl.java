package luj.cluster.internal.node.appactor.meta;

import java.util.Iterator;
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

  @Override
  public Iterator<Map.Entry<Class<?>, ActorMessageHandler<?, ?>>> iterator() {
    return _handlerMap.entrySet().iterator();
  }

  private final Map<Class<?>, ActorMessageHandler<?, ?>> _handlerMap;
}
