package luj.cluster.internal.node.start.actor.trigger;

import akka.actor.ActorRef;
import luj.cluster.api.node.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;

final class ActorImpl implements NodeStartListener.Actor {

  ActorImpl(ActorRef appRootRef, Class<?> actorType) {
    _appRootRef = appRootRef;
    _actorType = actorType;
  }

  @Override
  public void tell(Object msg) {
    AppRouteMsg routeMsg = new AppRouteMsg(_actorType, msg);
    _appRootRef.tell(routeMsg, ActorRef.noSender());
  }

  private final ActorRef _appRootRef;

  private final Class<?> _actorType;
}
