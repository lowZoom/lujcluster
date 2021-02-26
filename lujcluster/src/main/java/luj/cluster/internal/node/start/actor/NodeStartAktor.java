package luj.cluster.internal.node.start.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import java.util.function.Consumer;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class NodeStartAktor extends AbstractActor {

  public static Props props(ClusterBeanCollector.Result collectResult,
      Object startParam, Consumer<ActorRef> receiveRefHolder, boolean clusterEnabled) {
    return Props.create(NodeStartAktor.class, () -> new NodeStartAktor(
        collectResult, receiveRefHolder, startParam, clusterEnabled));
  }

  NodeStartAktor(ClusterBeanCollector.Result collectResult,
      Consumer<ActorRef> receiveRefHolder, Object startParam, boolean clusterEnabled) {
    _collectResult = collectResult;
    _receiveRefHolder = receiveRefHolder;
    _startParam = startParam;
    _clusterEnabled = clusterEnabled;
  }

  @Override
  public void preStart() throws Exception {
    new PreStart(this, context(), _collectResult,
        _receiveRefHolder, _startParam, _clusterEnabled).run();
  }

  @Override
  public Receive createReceive() {
    return emptyBehavior();
  }

  private final ClusterBeanCollector.Result _collectResult;

  private final Consumer<ActorRef> _receiveRefHolder;
  private final Object _startParam;

  private final boolean _clusterEnabled;
}
