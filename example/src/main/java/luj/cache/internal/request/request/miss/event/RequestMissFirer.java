package luj.cache.internal.request.request.miss.event;

import java.util.List;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheKey;
import luj.cache.internal.container.CacheContainerState;

public interface RequestMissFirer {

  interface Factory {

    static RequestMissFirer create(CacheContainerState containerState, CacheContainer containerFacade,
        Object reqParam, List<CacheKey> missKeyList) {
      return new RequestMissFirerImpl(missKeyList, containerState, reqParam, containerFacade);
    }
  }

  void fire();
}
