package luj.cluster.internal.node.appactor.akka.root;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;

final class OnAppRoute implements FI.UnitApply<AppRouteMsg> {

  OnAppRoute(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(AppRouteMsg i) {
    Class<?> appType = i.getActorType();
    ActorRef akkaRef = _rootAktor.getChildRefMap().get(appType);

    Object msg = i.getAppMsg();
    checkNotNull(akkaRef, "%s(%s)", appType.getName(), msg.getClass().getName());

    akkaRef.tell(msg, _rootAktor.sender());
  }

  private final AppRootAktor _rootAktor;
}
