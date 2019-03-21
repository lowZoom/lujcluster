package luj.cluster.internal.node.appactor.meta;

import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

public interface ActorMeta {

  ActorPreSstartHandler<?> getPreStartHandler();

  ActorMessageHandleMap getMessageHandleMap();
}
