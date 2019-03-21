package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

public interface ActorMeta {

  ActorPreStartHandler<?> getPreStartHandler();

  ActorMessageHandleMap getMessageHandleMap();
}
