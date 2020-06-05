package luj.cluster.internal.node.message.listener;

import akka.actor.ActorRef;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;

final class ListenActorImpl implements NodeMessageListener.Actor {

  ListenActorImpl(Class<?> actorType, ActorRef rootRef) {
    _actorType = actorType;
    _rootRef = rootRef;
  }

  @Override
  public void tell(Object msg) {
    _rootRef.tell(new AppRouteMsg(_actorType, msg), ActorRef.noSender());
  }

  private final Class<?> _actorType;

  /**
   * @see luj.cluster.internal.node.appactor.akka.root.AppRootAktor
   */
  private final ActorRef _rootRef;
}
