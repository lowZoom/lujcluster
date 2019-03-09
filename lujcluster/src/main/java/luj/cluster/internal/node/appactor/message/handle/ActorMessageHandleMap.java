package luj.cluster.internal.node.appactor.message.handle;

import luj.cluster.api.actor.ActorMessageHandler;

public interface ActorMessageHandleMap {

  ActorMessageHandler<?, ?> getHandler(Class<?> msgType);
}
