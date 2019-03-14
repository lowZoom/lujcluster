package luj.game.api.data;

import java.util.function.Function;
import luj.data.type.JRef;
import org.omg.CORBA.NO_IMPLEMENT;

final class AndLoadImpl<R, F> implements PlayerDataLoad.AndLoad<R, F> {

  @Override
  public <NF> PlayerDataLoad.AndLoad join(Function<F, JRef<NF>> from, Function<R, NF> to) {
    throw new NO_IMPLEMENT("join尚未实现");
  }

//  private final CacheRequest _req;
}
