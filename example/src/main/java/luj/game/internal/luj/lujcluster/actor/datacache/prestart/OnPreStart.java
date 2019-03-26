package luj.game.internal.luj.lujcluster.actor.datacache.prestart;

import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;

@Internal
final class OnPreStart implements ActorPreStartHandler<DataActorState> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState cacheActor = ctx.getActorState(this);
    Actor cacheRef = ctx.getActor();

    Actor loadRef = ctx.createActor(new DataLoadActor(cacheRef));
    cacheActor.setLoadRef(loadRef);
  }
}
