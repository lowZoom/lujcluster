package luj.game.internal.data.collect;

import luj.ava.reflect.generic.GenericType;
import luj.game.api.data.PlayerDataLoad;

final class LoadImpl implements DataCmdMapCollectorImpl.Load {

  LoadImpl(PlayerDataLoad<?> loader) {
    _loader = loader;
  }

  @Override
  public Class<?> getLoadResultType() {
    return GenericType.fromSubclassInstance(_loader, PlayerDataLoad.class).getTypeArg(0);
  }

  @Override
  public PlayerDataLoad<?> asReq() {
    return _loader;
  }

  private final PlayerDataLoad<?> _loader;
}
