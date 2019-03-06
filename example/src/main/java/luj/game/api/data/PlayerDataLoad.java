package luj.game.api.data;

import java.util.function.Function;
import luj.data.type.JRef;

public interface PlayerDataLoad<R> {

  interface Context<R> {

    <F> AndLoad<R, F> load(Function<R, F> field);
  }

  interface AndLoad<R, F> {

    <NF> AndLoad join(Function<F, JRef<NF>> from, Function<R, NF> to);
  }

  void load(Context<R> ctx);
}
