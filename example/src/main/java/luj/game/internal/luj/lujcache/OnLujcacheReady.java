package luj.game.internal.luj.lujcache;

import luj.ava.spring.Internal;
import luj.cache.api.listener.CacheReadyListener;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.cmd.exec.ExecutePlayerCmdMsg;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;

@Internal
final class OnLujcacheReady implements CacheReadyListener {

  /**
   * @see luj.game.internal.luj.lujcluster.actor.datacache.loadrsp.OnFinishLoadData#onHandle
   * @see DataActorState
   */
  @Override
  public void onReady(Context ctx) {
    JamreqInLujcache param = ctx.getRequestParam();
    ActorPreStartHandler.Actor execRef = param.getCacheActor().getExecRef();

    PlayerDataCommand<?> cmd = param.getDataCmd();
    execRef.tell(new ExecutePlayerCmdMsg(cmd, ctx.getResultBuilder()));
  }
}
