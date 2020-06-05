package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.logging.LogFactory;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public class AppMessageHandleInvoker {

  public AppMessageHandleInvoker(AppAktor appAktor, ActorMessageHandler<?, ?> handler, Object msg,
      ActorRef senderRef) {
    _appAktor = appAktor;
    _handler = handler;
    _msg = msg;
    _senderRef = senderRef;
  }

  public void invoke() {
    //FIXME: temp
    Log log = LogFactory.get(_appAktor, _handler);

    RemoteNodeImpl remoteNode = new RemoteNodeImpl(_senderRef, _appAktor.self());
    HandleContextImpl ctx = new HandleContextImpl(_appAktor, log, _msg, remoteNode);

    try {
      _handler.onHandle(ctx);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private final AppAktor _appAktor;

  private final ActorMessageHandler<?, ?> _handler;
  private final Object _msg;

  private final ActorRef _senderRef;
}
