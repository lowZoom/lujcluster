package luj.cluster.internal.node.appactor.akka.root;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.instance.create.AppAktorCreator;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnCreateAppActor implements FI.UnitApply<CreateAppActorMsg> {

  OnCreateAppActor(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(CreateAppActorMsg i) {
    Class<?> appType = i.getActorType();
//    LOG.debug("[cluster]创建应用actor：{}", appType.getSimpleName());

    ActorRef appRef = new AppAktorCreator(_rootAktor.getActorMetaMap(), appType, i.getActorState(),
        _rootAktor.getMemberRef(), _rootAktor.context()).create();

    _rootAktor.getChildRefMap().put(appType, appRef);
  }

//  private static final Logger LOG = LoggerFactory.getLogger(OnCreateAppActor.class);

  private final AppRootAktor _rootAktor;
}
