package luj.cache.api.container;

public interface CacheEntry {

  enum Presence {

    LOADING,

    PRESENT,

    ABSENT,
  }


  void setPresence(Presence value);

  Presence getPresence();

  void setData(Object data);

  Object getData();
}
