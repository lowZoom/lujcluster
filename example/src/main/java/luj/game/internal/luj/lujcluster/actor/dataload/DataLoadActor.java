package luj.game.internal.luj.lujcluster.actor.dataload;

import luj.cluster.api.message.NodeMessageListener;

public class DataLoadActor {

  public DataLoadActor(NodeMessageListener.Actor cacheActor) {
    _cacheActor = cacheActor;
  }

  public NodeMessageListener.Actor getCacheActor() {
    return _cacheActor;
  }

  private final NodeMessageListener.Actor _cacheActor;
}
