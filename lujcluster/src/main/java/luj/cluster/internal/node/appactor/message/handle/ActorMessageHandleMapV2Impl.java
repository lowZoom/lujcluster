package luj.cluster.internal.node.appactor.message.handle;

import com.google.common.collect.Multimap;
import java.util.Collection;

final class ActorMessageHandleMapV2Impl implements ActorMessageHandleMapV2 {

  @Override
  public Collection<Class<?>> getHandleList(String msgKey) {
    return _handleMap.get(msgKey);
  }

  Multimap<String, Class<?>> _handleMap;
}
