package luj.cluster.internal.node.appactor.message.handle;

import java.util.Collection;

public interface ActorMessageHandleMapV2 {

  Collection<Class<?>> getHandleList(String msgKey);
}
