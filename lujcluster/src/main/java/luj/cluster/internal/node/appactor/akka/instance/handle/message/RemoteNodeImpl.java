package luj.cluster.internal.node.appactor.akka.instance.handle.message;

import luj.cluster.api.actor.ActorMessageHandler;

final class RemoteNodeImpl implements ActorMessageHandler.Node {

  RemoteNodeImpl(String ip) {
    _ip = ip;
  }

  @Override
  public String getIp() {
    return _ip;
  }

  private final String _ip;
}
