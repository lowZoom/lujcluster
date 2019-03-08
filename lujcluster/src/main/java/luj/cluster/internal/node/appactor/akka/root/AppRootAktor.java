package luj.cluster.internal.node.appactor.akka.root;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;

public final class AppRootAktor extends AbstractActor {

  public static Props props() {
    return Props.create(AppRootAktor.class, () -> new AppRootAktor(new HashMap<>()));
  }

  AppRootAktor(Map<Class<?>, ActorRef> childMap) {
    _childMap = childMap;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(CreateAppActorMsg.class, new OnCreateAppActor(this))
        .match(AppRouteMsg.class, new OnAppRoute(this))
        .build();
  }

  public Map<Class<?>, ActorRef> getChildMap() {
    return _childMap;
  }

  private final Map<Class<?>, ActorRef> _childMap;
}
