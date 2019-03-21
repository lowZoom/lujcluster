package luj.game.internal.luj.lujcluster.actor.datacache.prestart;

import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;

@Internal
final class OnPreSstart implements ActorPreSstartHandler<DataActorState> {

  @Override
  public void onHandle(Context ctx) {
    Actor cacheRef = ctx.getActor();
    Actor loadRef = ctx.createActor(new DataLoadActor(cacheRef));

    DataActorState cacheActor = ctx.getActorState(this);
    cacheActor.setLoadRef(loadRef);
  }
}
