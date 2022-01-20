package luj.cluster.internal.node.appactor.akka.instance;

import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.remote.RemoteMsgHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;

final class OnMessageFromRemote implements FI.UnitApply<MessageFromRemote> {

  OnMessageFromRemote(AppAktor appAktor) {
    _appAktor = appAktor;
  }

  @Override
  public void apply(MessageFromRemote i) {
    RemoteMsgHandleInvoker.GET.invoke(_appAktor, i);
  }

  private final AppAktor _appAktor;
}
