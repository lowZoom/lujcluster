package luj.game.internal.luj.lujcluster.actor.gameplay.data.load;

import luj.cluster.api.actor.ActorPreStartHandler;

public class DataLoadActor {

  public DataLoadActor(ActorPreStartHandler.Actor cacheRef) {
    _cacheRef = cacheRef;
  }

  public ActorPreStartHandler.Actor getCacheRef() {
    return _cacheRef;
  }

//  private final PersistSession _lujpersist;

  private final ActorPreStartHandler.Actor _cacheRef;
}
