package luj.cluster.internal.node.appactor.akka.root;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.HashMap;
import java.util.Map;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;

/**
 * 应用层actor根节点
 */
public class AppRootAktor extends AbstractActor {

  public static Props props(ActorMetaMap actorMetaMap) {
    return Props.create(AppRootAktor.class, () -> new AppRootAktor(actorMetaMap, new HashMap<>()));
  }

  AppRootAktor(ActorMetaMap actorMetaMap, Map<Class<?>, ActorRef> childRefMap) {
    _actorMetaMap = actorMetaMap;
    _childRefMap = childRefMap;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(CreateAppActorMsg.class, new OnCreateAppActor(this))
        .match(AppRouteMsg.class, new OnAppRoute(this))
        .build();
  }

  public ActorMetaMap getActorMetaMap() {
    return _actorMetaMap;
  }

  public Map<Class<?>, ActorRef> getChildRefMap() {
    return _childRefMap;
  }

  private final ActorMetaMap _actorMetaMap;

  private final Map<Class<?>, ActorRef> _childRefMap;
}
