package luj.game.internal.luj.lujcluster.actor.datacache.prestart;

import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.internal.luj.lujcluster.actor.cmd.DataCmdActor;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;

@Internal
final class OnPreStart implements ActorPreStartHandler<DataActorState> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState cacheActor = ctx.getActorState(this);
    Actor cacheRef = ctx.getActor();

    createLoadActor(ctx, cacheActor, cacheRef);
    createExecActor(ctx, cacheActor);
  }

  private void createLoadActor(Context ctx, DataActorState cacheActor, Actor cacheRef) {
    Actor loadRef = ctx.createActor(new DataLoadActor(cacheRef));
    cacheActor.setLoadRef(loadRef);
  }

  private void createExecActor(Context ctx, DataActorState cacheActor) {
    Actor execRef = ctx.createActor(new DataCmdActor());
    cacheActor.setExecRef(execRef);
  }
}
