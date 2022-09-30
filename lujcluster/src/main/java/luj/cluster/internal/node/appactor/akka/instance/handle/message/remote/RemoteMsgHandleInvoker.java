package luj.cluster.internal.node.appactor.akka.instance.handle.message.remote;

import akka.actor.ActorRef;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.instance.handle.handler.AppMsgHandleFinder;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.local.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;

public enum RemoteMsgHandleInvoker {
  GET;

  public void invoke(AppAktor actor, MessageFromRemote message) {
    String msgKey = message.getMsgKey();
    Object appMsg = message.getMsg();
    AppMsgHandleFinder.Handler handler = new AppMsgHandleFinder(actor, msgKey, appMsg).find();

    if (handler.isAbsent()) {
      handler.handleAbsent();
      return;
    }

    RemoteNodeImpl remoteNode = new RemoteNodeImpl();
    ActorRef memberRef = actor.getClusterMemberRef();
    remoteNode._memberRef = memberRef;

    remoteNode._targetHost = message.getSenderHost();
    remoteNode._targetPort = message.getSenderPort();

    AppMessageHandleInvoker.GET.invoke(actor, handler.get(), appMsg, memberRef, remoteNode);
  }
}
