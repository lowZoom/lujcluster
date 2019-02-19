package luj.cluster.internal.node;

import akka.actor.ActorSystem;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.ConfigFactory;
import java.util.List;
import luj.cluster.api.start.NodeStartListener;
import luj.cluster.internal.node.actor.NodeStartActor;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  ClusterNodeStarterImpl(List<NodeStartListener> startListeners) {
    _startListeners = startListeners;
  }

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("LujCluster",
        ConfigFactory.parseString("akka{loglevel=DEBUG\nstdout-loglevel=DEBUG}"));

    sys.actorOf(NodeStartActor.props(nonnull(_startListeners)));
  }

  private <T> List<T> nonnull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  private final List<NodeStartListener> _startListeners;
}
