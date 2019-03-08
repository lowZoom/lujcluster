package luj.cluster.internal.node.appactor.akka.root;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import java.util.Map;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;

final class OnCreateAppActor implements FI.UnitApply<CreateAppActorMsg> {

  OnCreateAppActor(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(CreateAppActorMsg i) throws Exception {
    Class<?> appType = i.getActorType();

    ActorRef appRef = _rootAktor.context().actorOf(AppAktor.props(null));

    Map<Class<?>, ActorRef> childMap = _rootAktor.getChildMap();
    childMap.put(appType, appRef);
  }

  private final AppRootAktor _rootAktor;
}
