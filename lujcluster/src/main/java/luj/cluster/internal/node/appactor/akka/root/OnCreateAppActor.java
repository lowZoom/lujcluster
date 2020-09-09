package luj.cluster.internal.node.appactor.akka.root;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;

final class OnCreateAppActor implements FI.UnitApply<CreateAppActorMsg> {

  OnCreateAppActor(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(CreateAppActorMsg i) {
    Class<?> appType = i.getActorType();

    ActorRef appRef = new AppAktorCreator(_rootAktor.getActorMetaMap(), appType, i.getActorState(),
        _rootAktor.getMemberRef(), _rootAktor.context()).create();

    _rootAktor.getChildRefMap().put(appType, appRef);
  }

  private final AppRootAktor _rootAktor;
}
