package luj.game.internal.luj.lujcluster.actor.cmd.exec;

import luj.game.api.data.PlayerDataCommand;
import luj.game.internal.luj.lujcluster.actor.cmd.DataCmdActorReceive;

final class OnExecuteDataCmd implements DataCmdActorReceive<ExecutePlayerCmdMsg> {

  @Override
  public void onHandle(Context ctx) {
    ExecutePlayerCmdMsg msg = ctx.getMessage(this);

    PlayerDataCommand<?> cmd = msg.getCommand();

    Object loadResult = msg.getResultBuilder().build();

    CommandContextImpl cmdCtx = new CommandContextImpl(loadResult);
    cmd.execute(cmdCtx);
  }
}
