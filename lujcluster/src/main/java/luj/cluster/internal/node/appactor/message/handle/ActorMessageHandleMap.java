package luj.cluster.internal.node.appactor.message.handle;

import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;

public interface ActorMessageHandleMap extends
    Iterable<Map.Entry<String, ActorMessageHandler<?, ?>>> {

  ActorMessageHandler<?, ?> getHandler(String msgKey);
}
