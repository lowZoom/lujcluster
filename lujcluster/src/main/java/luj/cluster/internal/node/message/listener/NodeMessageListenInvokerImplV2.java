package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import com.google.common.collect.Multimap;
import java.util.Collection;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;

final class NodeMessageListenInvokerImplV2 implements NodeMessageListenInvoker {

  NodeMessageListenInvokerImplV2(String messageKey, Object message,
      Multimap<String, Class<?>> handleMap, ActorRef appRootRef) {
    _messageKey = messageKey;
    _message = message;
    _handleMap = handleMap;
    _appRootRef = appRootRef;
  }

  @Override
  public void invoke() {
    Collection<Class<?>> actorTypes = _handleMap.get(_messageKey);
    for (Class<?> type : actorTypes) {
      _appRootRef.tell(new AppRouteMsg(type, _message), ActorRef.noSender());
    }
  }

  private final String _messageKey;
  private final Object _message;

  private final Multimap<String, Class<?>> _handleMap;
  private final ActorRef _appRootRef;
}
