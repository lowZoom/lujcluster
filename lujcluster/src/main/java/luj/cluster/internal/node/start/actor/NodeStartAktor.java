package luj.cluster.internal.node.start.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.function.Consumer;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class NodeStartAktor extends AbstractActor {

  public static Props props(ClusterBeanCollector.Result collectResult,
      ClusterNodeStarter.Config nodeConfig, boolean clusterEnabled,
      Consumer<ActorRef> receiveRefHolder) {
    return Props.create(NodeStartAktor.class, () -> new NodeStartAktor(
        collectResult, nodeConfig, clusterEnabled, receiveRefHolder));
  }

  NodeStartAktor(ClusterBeanCollector.Result collectResult, ClusterNodeStarter.Config nodeConfig,
      boolean clusterEnabled, Consumer<ActorRef> receiveRefHolder) {
    _collectResult = collectResult;
    _nodeConfig = nodeConfig;
    _clusterEnabled = clusterEnabled;
    _receiveRefHolder = receiveRefHolder;
  }

  @Override
  public void preStart() throws Exception {
    new PreStart(this, context(), _collectResult,
        _nodeConfig, _clusterEnabled, _receiveRefHolder).run();
  }

  @Override
  public Receive createReceive() {
    return emptyBehavior();
  }

  private final ClusterBeanCollector.Result _collectResult;

  private final ClusterNodeStarter.Config _nodeConfig;
  private final boolean _clusterEnabled;

  private final Consumer<ActorRef> _receiveRefHolder;
}
