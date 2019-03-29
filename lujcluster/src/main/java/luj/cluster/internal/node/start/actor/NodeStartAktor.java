package luj.cluster.internal.node.start.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class NodeStartAktor extends AbstractActor {

  public static Props props(ClusterBeanCollector.Result collectResult, Object startParam) {
    return Props.create(NodeStartAktor.class, () -> new NodeStartAktor(collectResult, startParam));
  }

  NodeStartAktor(ClusterBeanCollector.Result collectResult, Object startParam) {
    _collectResult = collectResult;
    _startParam = startParam;
  }

  @Override
  public void preStart() throws Exception {
    new PreStart(this, context(), _collectResult).run();
  }

  @Override
  public Receive createReceive() {
    return emptyBehavior();
  }

  Object getStartParam() {
    return _startParam;
  }

  private final ClusterBeanCollector.Result _collectResult;

  private final Object _startParam;
}
