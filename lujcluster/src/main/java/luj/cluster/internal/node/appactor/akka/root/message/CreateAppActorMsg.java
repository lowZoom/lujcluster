package luj.cluster.internal.node.appactor.akka.root.message;

public class CreateAppActorMsg {

  public CreateAppActorMsg(Class<?> actorType) {
    _actorType = actorType;
  }

  public Class<?> getActorType() {
    return _actorType;
  }

  private final Class<?> _actorType;
}
