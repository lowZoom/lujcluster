package luj.cluster.internal.node.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import java.util.List;
import luj.cluster.api.start.NodeStartListener;

public final class NodeStartActor extends AbstractActor {

  public static Props props(List<NodeStartListener> startListeners) {
    ContextImpl context = new ContextImpl(null);
    PreStart preStart = new PreStart(startListeners, context);

    return Props.create(NodeStartActor.class, () -> new NodeStartActor(preStart));
  }

  NodeStartActor(PreStart preStart) {
    _preStart = preStart;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder().build();
  }

  @Override
  public void preStart() {
    _preStart.run();
  }

  private final PreStart _preStart;
}
