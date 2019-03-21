package luj.game.internal.data.collect;

import luj.ava.reflect.type.TypeX;
import luj.game.api.data.PlayerDataCommand;

final class CmdImpl implements DataCmdMapCollectorImpl.Cmd {

  CmdImpl(PlayerDataCommand<?> cmd, Class<?> cmdType) {
    _cmd = cmd;
    _cmdType = cmdType;
  }

  @Override
  public Class<?> getCommandType() {
    return _cmdType;
  }

  @Override
  public Class<?> getLoadResultType() {
    return TypeX.of(_cmdType)
        .getSupertype(PlayerDataCommand.class)
        .getTypeParam(0)
        .asClass();
  }

  @Override
  public PlayerDataCommand asCommand() {
    return _cmd;
  }

  private final PlayerDataCommand<?> _cmd;

  private final Class<?> _cmdType;
}
