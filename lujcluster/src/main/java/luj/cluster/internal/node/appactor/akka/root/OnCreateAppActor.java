package luj.cluster.internal.node.appactor.akka.root;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;
import luj.cluster.internal.node.appactor.meta.ActorMeta;

final class OnCreateAppActor implements FI.UnitApply<CreateAppActorMsg> {

  OnCreateAppActor(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(CreateAppActorMsg i) {
    Class<?> appType = i.getActorType();

    ActorMeta meta = _rootAktor.getActorMetaMap().getMeta(appType);
    checkNotNull(meta, appType);

    ActorRef appRef = _rootAktor.context().actorOf(AppAktor.props(i.getActorState(), meta));

    _rootAktor.getChildRefMap().put(appType, appRef);
  }

  private final AppRootAktor _rootAktor;
}
