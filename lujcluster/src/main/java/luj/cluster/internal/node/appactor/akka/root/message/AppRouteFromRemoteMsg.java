package luj.cluster.internal.node.appactor.akka.root.message;

import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;

public class AppRouteFromRemoteMsg {

  public AppRouteFromRemoteMsg(Class<?> actorType, MessageFromRemote message) {
    _actorType = actorType;
    _message = message;
  }

  public Class<?> getActorType() {
    return _actorType;
  }

  public MessageFromRemote getMessage() {
    return _message;
  }

  private final Class<?> _actorType;

  private final MessageFromRemote _message;
}
