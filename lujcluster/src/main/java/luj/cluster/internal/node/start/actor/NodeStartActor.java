package luj.cluster.internal.node.start.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public final class NodeStartActor extends AbstractActor {

  public static Props props(ClusterBeanCollector.Result collectResult, Object startParam) {
    return Props.create(NodeStartActor.class, () -> new NodeStartActor(collectResult, startParam));
  }

  NodeStartActor(ClusterBeanCollector.Result collectResult, Object startParam) {
    _collectResult = collectResult;
    _startParam = startParam;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().build();
  }

  @Override
  public void preStart() throws Exception {
    new PreStart(this).run();
  }

  ClusterBeanCollector.Result getCollectResult() {
    return _collectResult;
  }

  Object getStartParam() {
    return _startParam;
  }

  private final ClusterBeanCollector.Result _collectResult;

  private final Object _startParam;
}
