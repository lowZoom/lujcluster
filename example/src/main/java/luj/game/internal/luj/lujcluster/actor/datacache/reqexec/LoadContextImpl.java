package luj.game.internal.luj.lujcluster.actor.datacache.reqexec;

import java.util.function.Function;
import luj.game.api.data.PlayerDataLoad;
import org.omg.CORBA.NO_IMPLEMENT;

final class LoadContextImpl implements PlayerDataLoad.Context<Object> {

  @Override
  public <F> PlayerDataLoad.AndLoad<Object, F> load(Function<Object, F> field) {
    throw new NO_IMPLEMENT("load尚未实现");
//    return _lujcache.createRequest();
  }

//  private final CacheSession _lujcache;
}
