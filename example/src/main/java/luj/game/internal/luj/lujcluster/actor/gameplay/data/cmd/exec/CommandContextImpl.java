package luj.game.internal.luj.lujcluster.actor.gameplay.data.cmd.exec;

import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataCommand.Context;
import luj.game.api.data.PlayerDataCommand.Player;
import org.omg.CORBA.NO_IMPLEMENT;

final class CommandContextImpl implements Context {

  CommandContextImpl(Object loadResult) {
    _loadResult = loadResult;
  }

  @Override
  public <D> D data(PlayerDataCommand<D> cmd) {
    return (D) _loadResult;
  }

  @Override
  public Player player() {
    throw new NO_IMPLEMENT("player尚未实现");
  }

  private final Object _loadResult;
}
