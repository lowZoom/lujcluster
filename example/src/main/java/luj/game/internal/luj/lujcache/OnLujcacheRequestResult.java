package luj.game.internal.luj.lujcache;

import luj.ava.spring.Internal;
import luj.cache.api.listener.RequestResultFactory;

@Internal
final class OnLujcacheRequestResult implements RequestResultFactory {

  @Override
  public Object create(Context ctx) {
    try {
      Class<?> resultType = Class.forName(ctx.getResultType().getName());// + "Impl");
      return resultType.getConstructor().newInstance();

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
