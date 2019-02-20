package luj.game.api.data;

import java.util.function.Function;
import luj.data.type.JRef;

public interface GameDataLoad<R> {

  interface Context<R> {

    R getData();

    <F> AndLoad<R, F> load(Function<R, F> field);
  }

  interface AndLoad<R, F> {

    <NF> AndLoad load(Function<F, JRef<NF>> from, Function<R, NF> to);
  }

  void load(Context<R> ctx);
}
