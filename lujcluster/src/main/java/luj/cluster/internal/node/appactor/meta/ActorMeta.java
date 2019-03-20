package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPrestartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

public interface ActorMeta {

  ActorPrestartHandler<?> getPrestartHandler();

  ActorMessageHandleMap getMessageHandleMap();
}
