package luj.cluster.internal.node.appactor.akka.instance.create;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.meta.ActorMeta;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;

final class AppAktorCreatorImpl implements AppAktorCreator {

  AppAktorCreatorImpl(ActorMetaMap actorMetaMap,
      Class<?> actorType, Object actorState, ActorContext aktorCtx) {
    _actorMetaMap = actorMetaMap;

    _actorType = actorType;
    _actorState = actorState;

    _aktorCtx = aktorCtx;
  }

  @Override
  public ActorRef create() {
    ActorMeta meta = _actorMetaMap.getMeta(_actorType);
    checkNotNull(meta, _actorType);

    return _aktorCtx.actorOf(AppAktor.props(_actorState, meta, _actorMetaMap));
  }

  private final ActorMetaMap _actorMetaMap;

  private final Class<?> _actorType;
  private final Object _actorState;

  private final ActorContext _aktorCtx;
}
