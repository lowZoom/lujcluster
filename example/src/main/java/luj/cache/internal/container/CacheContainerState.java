package luj.cache.internal.container;

import luj.cache.api.listener.CacheMissListener;
import luj.cache.api.listener.CacheReadyListener;

public class CacheContainerState {

  public CacheContainerState(CacheMissListener entryMissListener,
      CacheReadyListener requestReadyListener) {
    _entryMissListener = entryMissListener;
    _requestReadyListener = requestReadyListener;
  }

  public CacheMissListener getEntryMissListener() {
    return _entryMissListener;
  }

  public CacheReadyListener getRequestReadyListener() {
    return _requestReadyListener;
  }

  private final CacheMissListener _entryMissListener;

  private final CacheReadyListener _requestReadyListener;
}
