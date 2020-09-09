package luj.cluster.internal.node.appactor.akka.instance.create;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.actor.Props;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.meta.ActorMeta;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;

public class AppAktorCreator {

  public AppAktorCreator(ActorMetaMap actorMetaMap, Class<?> actorType, Object actorState,
      ActorRef clusterMemberRef, ActorContext aktorCtx) {
    _actorMetaMap = actorMetaMap;
    _actorType = actorType;

    _actorState = actorState;
    _clusterMemberRef = clusterMemberRef;

    _aktorCtx = aktorCtx;
  }

  public ActorRef create() {
    checkNotNull(_clusterMemberRef);

    ActorMeta meta = _actorMetaMap.getMeta(_actorType);
    checkNotNull(meta, _actorType);

    Props props = AppAktor.props(_actorState, meta, _actorMetaMap, _clusterMemberRef);
    return _aktorCtx.actorOf(props);//, _actorType.getSimpleName());
  }

  private final ActorMetaMap _actorMetaMap;
  private final Class<?> _actorType;

  private final Object _actorState;
  private final ActorRef _clusterMemberRef;

  private final ActorContext _aktorCtx;
}
