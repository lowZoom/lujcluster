package luj.game.internal.luj.lujcluster.actor.cmd.exec;

import luj.ava.spring.Internal;
import luj.cluster.api.logging.Log;
import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.cmd.DataCmdActorReceive;

@Internal
final class OnExecuteDataCmd implements DataCmdActorReceive<ExecutePlayerCmdMsg> {

  @Override
  public void onHandle(Context ctx) {
    ExecutePlayerCmdMsg msg = ctx.getMessage(this);

    PlayerDataCommand<?> cmd = msg.getDataCommand();

    Log log = ctx.getLogger();
    log.debug("执行数据cmd：{}", cmd.getClass().getSimpleName());

    Object loadResult = msg.getDataResult();

    CommandContextImpl cmdCtx = new CommandContextImpl(loadResult);
    cmd.execute(cmdCtx);

    //TODO: 归还解锁缓存
  }
}
