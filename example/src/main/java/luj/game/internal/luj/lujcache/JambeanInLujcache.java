package luj.game.internal.luj.lujcache;

import luj.cluster.api.message.NodeMessageListener;

public class JambeanInLujcache {

  public JambeanInLujcache(NodeMessageListener.Actor loadActor) {
    _loadActor = loadActor;
  }

  public NodeMessageListener.Actor getLoadActor() {
    return _loadActor;
  }

  private final NodeMessageListener.Actor _loadActor;
}
