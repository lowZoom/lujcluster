package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.handle.handler.AppMsgHandleFinder;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.local.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromLocal;

final class OnMessageFromLocal implements FI.UnitApply<MessageFromLocal> {

  OnMessageFromLocal(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @Override
  public void apply(MessageFromLocal i) {
    String msgKey = i.getMsgKey();
    Object appMsg = i.getAppMsg();
    AppMsgHandleFinder.Handler handler = new AppMsgHandleFinder(_appAktor, msgKey, appMsg).find();

    if (handler.isAbsent()) {
      handler.handleAbsent();
      return;
    }

    ActorRef memberRef = _appAktor.getClusterMemberRef();
    ActorMessageHandler.Node remoteNode = i.getRemoteNode();

    AppMessageHandleInvoker.GET.invoke(_appAktor, handler.get(), appMsg, memberRef, remoteNode);
  }

  private final AppAktor _appAktor;
}
