package luj.cluster.internal.node.appactor.akka.root.message;

public class AppRouteMsg {

  public AppRouteMsg(Class<?> actorType, Object appMsg) {
    _actorType = actorType;
    _appMsg = appMsg;
  }

  public Class<?> getActorType() {
    return _actorType;
  }

  public Object getAppMsg() {
    return _appMsg;
  }

  private final Class<?> _actorType;

  private final Object _appMsg;
}
