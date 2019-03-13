package luj.game.internal.data.collect;

import luj.ava.reflect.generic.GenericType;
import luj.game.internal.data.collect.DataCmdMapCollectorImpl.Cmd;

final class CmdImpl implements Cmd {

  @Override
  public Class<?> getCommandType() {
    return _cmdType;
  }

  @Override
  public Class<?> getLoadResultType() {
    return GenericType.fromSubclass(_cmdType).getTypeArg(0);
  }

  private final Class<?> _cmdType;
}
