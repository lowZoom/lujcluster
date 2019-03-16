package luj.cache.internal.container.request;

import java.util.List;
import luj.cache.internal.container.CacheContainerState;
import luj.cache.internal.container.request.miss.MissingEntryCollector;
import org.omg.CORBA.NO_IMPLEMENT;

final class CacheDataRequestorImpl implements CacheDataRequestor {

  CacheDataRequestorImpl(CacheContainerState containerState) {
    _containerState = containerState;
  }

  @Override
  public void request() {
    List<Object> missList = MissingEntryCollector.Factory.create().collect();

    if (!missList.isEmpty()) {
      for (Object key : missList) {
        triggerListener(key);
      }
      return;
    }

    _containerState.getRequestReadyListener().onReady(null);
  }

  private void triggerListener(Object key) {
    throw new NO_IMPLEMENT("triggerListener尚未实现");
  }

  private final CacheContainerState _containerState;
}
