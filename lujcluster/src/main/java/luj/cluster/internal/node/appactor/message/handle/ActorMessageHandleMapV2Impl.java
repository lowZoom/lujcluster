package luj.cluster.internal.node.appactor.message.handle;

import com.google.common.collect.Multimap;
import java.util.Collection;

final class ActorMessageHandleMapV2Impl implements ActorMessageHandleMapV2 {

  ActorMessageHandleMapV2Impl(Multimap<String, Class<?>> handleMap) {
    _handleMap = handleMap;
  }

  @Override
  public Collection<Class<?>> getHandleList(String msgKey) {
    return _handleMap.get(msgKey);
  }

  private final Multimap<String, Class<?>> _handleMap;
}
