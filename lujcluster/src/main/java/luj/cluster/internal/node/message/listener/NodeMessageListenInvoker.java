package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import java.util.Collection;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeMessageListenInvoker {

  public NodeMessageListenInvoker(String messageKey, Object message,
      ActorMessageHandleMapV2 handleMap, ActorRef appRootRef, ActorRef senderRef) {
    _messageKey = messageKey;
    _message = message;
    _handleMap = handleMap;
    _appRootRef = appRootRef;
    _senderRef = senderRef;
  }

  public void invoke() {
    Collection<Class<?>> actorTypes = _handleMap.getHandleList(_messageKey);
    if (actorTypes.isEmpty()) {
      LOG.info("[cluster]消息没有处理器，忽略：{}", _messageKey);
      return;
    }

    for (Class<?> type : actorTypes) {
      _appRootRef.tell(new AppRouteMsg(type, _message), _senderRef);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeMessageListenInvoker.class);

  private final String _messageKey;
  private final Object _message;

  private final ActorMessageHandleMapV2 _handleMap;
  private final ActorRef _appRootRef;

  private final ActorRef _senderRef;
}
