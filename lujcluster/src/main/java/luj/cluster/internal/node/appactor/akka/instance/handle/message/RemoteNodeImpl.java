package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;

final class RemoteNodeImpl implements ActorMessageHandler.Node {

  RemoteNodeImpl(ActorRef remoteRef) {
    _remoteRef = remoteRef;
  }

  @Override
  public String getIp() {
    return _remoteRef.path().address().host().get();
  }

  private final ActorRef _remoteRef;
}
