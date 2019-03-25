package luj.game.internal.luj.lujcache;

import luj.ava.spring.Internal;
import luj.cache.api.listener.CacheReadyListener;
import luj.cluster.api.node.NodeMessageListener.Actor;
import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.cmd.exec.ExecutePlayerCmdMsg;

@Internal
final class OnLujcacheReady implements CacheReadyListener {

  @Override
  public void onReady(Context ctx) {
    Actor cmdActor = null;

    JamreqInLujcache param = ctx.getRequestParam();

    PlayerDataCommand<?> cmd = param.getDataCmd();

    cmdActor.tell(new ExecutePlayerCmdMsg(cmd, ctx.getResultBuilder()));
  }
}
