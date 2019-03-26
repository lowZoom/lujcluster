package luj.cache.internal.container;

import luj.cache.api.container.CacheEntry;

final class CacheEntryImpl implements CacheEntry {

  @Override
  public void setPresence(Presence presence) {
    _presence = presence;
  }

  @Override
  public Presence getPresence() {
    return _presence;
  }

  @Override
  public void setLock(boolean lock) {
    _lock = lock;
  }

  @Override
  public boolean isLock() {
    return _lock;
  }

  @Override
  public void setData(Object data) {
    _data = data;
  }

  @Override
  public Object getData() {
    return _data;
  }

  private Presence _presence;
  private boolean _lock;

  private Object _data;
}
