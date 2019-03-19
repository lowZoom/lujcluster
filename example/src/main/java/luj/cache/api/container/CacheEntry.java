package luj.cache.api.container;

public interface CacheEntry {

  enum Presence {

    LOADING,

    PRESENT,

    ABSENT,
  }

  void setPresence(Presence presence);

  Presence getPresence();

  void setLock(boolean lock);

  boolean isLock();

  void setData(Object data);

  Object getData();
}
