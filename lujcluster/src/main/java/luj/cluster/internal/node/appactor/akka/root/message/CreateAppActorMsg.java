package luj.cluster.internal.node.appactor.akka.root.message;

public class CreateAppActorMsg {

  public CreateAppActorMsg(Class<?> actorType, Object actorState) {
    _actorType = actorType;
    _actorState = actorState;
  }

  public Class<?> getActorType() {
    return _actorType;
  }

  public Object getActorState() {
    return _actorState;
  }

  private final Class<?> _actorType;

  private final Object _actorState;
}
