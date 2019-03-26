package luj.game.internal.luj.lujcluster.actor.dataload;

import luj.cluster.api.actor.ActorPreStartHandler;
import luj.persist.api.PersistSession;

public class DataLoadActor {

  public DataLoadActor(PersistSession lujpersist,
      ActorPreStartHandler.Actor cacheRef) {
    _lujpersist = lujpersist;
    _cacheRef = cacheRef;
  }

  public PersistSession getLujpersist() {
    return _lujpersist;
  }

  public ActorPreStartHandler.Actor getCacheRef() {
    return _cacheRef;
  }

  private final PersistSession _lujpersist;

  private final ActorPreStartHandler.Actor _cacheRef;
}
