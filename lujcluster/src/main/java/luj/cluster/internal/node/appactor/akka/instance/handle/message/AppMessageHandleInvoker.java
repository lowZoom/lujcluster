package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.logging.LogFactory;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public enum AppMessageHandleInvoker {
  GET;

  public void invoke(AppAktor appAktor, ActorMessageHandler<?, ?> handler, Object msg,
      ActorRef clusterMemberRef) {
    //FIXME: temp
    Log log = LogFactory.get(appAktor, handler);

    AbstractActor.ActorContext actorCtx = appAktor.getContext();
    RemoteNodeImpl remoteNode = new RemoteNodeImpl();
    remoteNode._remoteRef = actorCtx.getSender();
    remoteNode._localRef = clusterMemberRef;

    LocalNodeImpl localNode = new LocalNodeImpl();
    localNode._selfRef = appAktor.getSelf();
    localNode._clusterMemberRef = clusterMemberRef;

    HandleContextImpl ctx = new HandleContextImpl();
    ctx._appAktor = appAktor;
    ctx._logger = log;
    ctx._msg = msg;
    ctx._remoteNode = remoteNode;
    ctx._localNode = localNode;

    try {
      handler.onHandle(ctx);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
