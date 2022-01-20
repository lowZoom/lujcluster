package luj.cluster.internal.node.appactor.meta;

import java.util.Iterator;
import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

final class MessageHandleMapImpl implements ActorMessageHandleMap {

  @Override
  public ActorMessageHandler<?, ?> getHandler(String msgKey) {
    return _handlerMap.get(msgKey);
  }

  @Override
  public Iterator<Map.Entry<String, ActorMessageHandler<?, ?>>> iterator() {
    return _handlerMap.entrySet().iterator();
  }

  Map<String, ActorMessageHandler<?, ?>> _handlerMap;
}
