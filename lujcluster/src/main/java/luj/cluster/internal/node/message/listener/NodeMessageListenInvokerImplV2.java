package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import java.util.Collection;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class NodeMessageListenInvokerImplV2 implements NodeMessageListenInvoker {

  NodeMessageListenInvokerImplV2(String messageKey, Object message,
      ActorMessageHandleMapV2 handleMap, ActorRef appRootRef, ActorRef senderRef) {
    _messageKey = messageKey;
    _message = message;
    _handleMap = handleMap;
    _appRootRef = appRootRef;
    _senderRef = senderRef;
  }

  @Override
  public void invoke() {
    Collection<Class<?>> actorTypes = _handleMap.getHandleList(_messageKey);
    if (actorTypes.isEmpty()) {
      LOG.info("消息没有处理器，忽略：{}", _messageKey);
      return;
    }

    for (Class<?> type : actorTypes) {
      _appRootRef.tell(new AppRouteMsg(type, _message), _senderRef);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeMessageListenInvokerImplV2.class);

  private final String _messageKey;
  private final Object _message;

  private final ActorMessageHandleMapV2 _handleMap;
  private final ActorRef _appRootRef;

  private final ActorRef _senderRef;
}
