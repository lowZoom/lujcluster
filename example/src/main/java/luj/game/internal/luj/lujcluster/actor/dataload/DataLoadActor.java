package luj.game.internal.luj.lujcluster.actor.dataload;

import luj.cluster.api.actor.ActorPreSstartHandler;

public class DataLoadActor {

  public DataLoadActor(ActorPreSstartHandler.Actor cacheRef) {
    _cacheRef = cacheRef;
  }

  public ActorPreSstartHandler.Actor getCacheRef() {
    return _cacheRef;
  }

  private final ActorPreSstartHandler.Actor _cacheRef;
}
