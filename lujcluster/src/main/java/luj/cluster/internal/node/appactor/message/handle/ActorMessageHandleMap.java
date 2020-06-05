package luj.cluster.internal.node.appactor.message.handle;

import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;

public interface ActorMessageHandleMap extends
    Iterable<Map.Entry<Class<?>, ActorMessageHandler<?, ?>>> {

  ActorMessageHandler<?, ?> getHandler(Class<?> msgType);
}
