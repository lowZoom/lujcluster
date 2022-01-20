package luj.cluster.internal.node.message.receive.actor;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Collection;
import java.util.Map;
import luj.cluster.api.node.message.NodeMessageSerializer;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteFromRemoteMsg;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMapV2;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemote2Msg;
import luj.cluster.internal.node.message.serialize.invoke.MessageDeserializeInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnNodeSendRemote2 implements FI.UnitApply<NodeSendRemote2Msg> {

  OnNodeSendRemote2(NodeReceiveAktor self) {
    _self = self;
  }

  @Override
  public void apply(NodeSendRemote2Msg i) throws Exception {
    LOG.debug("[cluster]收到外部远程消息：{}", i.getMessageKey());

    Map<String, NodeMessageSerializer<?>> codecMap = _self.getMsgCodecMap();
    Object msgObj = MessageDeserializeInvoker.GET.invoke(i.getMessageData(), codecMap, null);

    sendToApp(i, msgObj);
  }

  private void sendToApp(NodeSendRemote2Msg raw, Object msgObj) {
    ActorMessageHandleMapV2 handleMap = _self.getMsgHandleMap();
    String msgKey = raw.getMessageKey();

    Collection<Class<?>> actorTypes = handleMap.getHandleList(msgKey);
    if (actorTypes.isEmpty()) {
      LOG.info("[cluster]消息没有处理器，忽略：{}", msgKey);
      return;
    }

    String senderHost = raw.getSenderHost();
    int senderPort = raw.getSenderPort();
    ActorRef appRoot = _self.getAppRootRef();
    ActorRef senderRef = _self.context().sender();

    for (Class<?> type : actorTypes) {
      MessageFromRemote wrap = new MessageFromRemote(msgKey, msgObj, senderHost, senderPort);
      appRoot.tell(new AppRouteFromRemoteMsg(type, wrap), senderRef);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnNodeSendRemote2.class);

  private final NodeReceiveAktor _self;
}
