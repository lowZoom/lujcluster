package luj.cluster.internal.node.appactor.akka.instance.handle.message.remote;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.local.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

public enum RemoteMsgHandleInvoker {
  GET;

  public void invoke(AppAktor actor, MessageFromRemote message) {
    ActorMessageHandleMap handlerMap = actor.getMeta().getMessageHandleMap();
    ActorMessageHandler<?, ?> handler = handlerMap.getHandler(message.getMsgKey());

    ActorContext ctx = actor.context();
    Object appMsg = message.getMsg();
    if (handler == null) {
      ctx.self().tell(appMsg, ctx.sender());
      return;
    }

    RemoteNodeImpl remoteNode = new RemoteNodeImpl();
    ActorRef memberRef = actor.getClusterMemberRef();
    remoteNode._memberRef = memberRef;

    remoteNode._targetHost = message.getSenderHost();
    remoteNode._targetPort = message.getSenderPort();

    AppMessageHandleInvoker.GET.invoke(actor, handler, appMsg, memberRef, remoteNode);
  }
}
