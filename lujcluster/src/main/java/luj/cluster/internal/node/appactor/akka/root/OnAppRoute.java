package luj.cluster.internal.node.appactor.akka.root;

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
    ActorRef akkaRef = _rootAktor.getChildMap().get(appType);

    akkaRef.tell(i.getAppMsg(), ActorRef.noSender());
  }

  private final AppRootAktor _rootAktor;
}
