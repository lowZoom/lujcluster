package luj.game.internal.luj.lujcache;

import luj.cache.api.listener.CacheReadyListener;
import luj.cache.api.request.CacheRequest;
import luj.cluster.api.message.NodeMessageListener.Actor;
import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.cmd.exec.ExecutePlayerCmdMsg;


final class OnLujcacheReady implements CacheReadyListener {

  @Override
  public void onReady(Context ctx) {
    Actor cmdActor = null;

    CacheRequest req = ctx.getRequest();
    PlayerDataCommand<?> cmd = req.getParam();

    cmdActor.tell(new ExecutePlayerCmdMsg(cmd, ctx.getResultBuilder()));
  }
}
