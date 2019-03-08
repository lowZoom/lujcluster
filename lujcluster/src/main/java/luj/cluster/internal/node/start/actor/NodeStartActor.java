package luj.cluster.internal.node.start.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public final class NodeStartActor extends AbstractActor {

  public static Props props(ClusterBeanCollector.Result collectResult) {
    return Props.create(NodeStartActor.class, () -> new NodeStartActor(collectResult));
  }

  private NodeStartActor(ClusterBeanCollector.Result collectResult) {
    _collectResult = collectResult;
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

  private final ClusterBeanCollector.Result _collectResult;
}
