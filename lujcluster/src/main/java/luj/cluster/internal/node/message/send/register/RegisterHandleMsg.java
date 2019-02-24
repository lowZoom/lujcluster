package luj.cluster.internal.node.message.send.register;

import akka.actor.ActorRef;
import java.util.Set;

public final class RegisterHandleMsg {

  public RegisterHandleMsg(Set<String> idSet, ActorRef handleRef) {
    _idSet = idSet;
    _handleRef = handleRef;
  }

  public Set<String> getIdSet() {
    return _idSet;
  }

  public ActorRef getHandleRef() {
    return _handleRef;
  }

  private final Set<String> _idSet;

  private final ActorRef _handleRef;
}
