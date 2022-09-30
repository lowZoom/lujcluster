package luj.cluster.internal.node.shutdown;

import akka.Done;
import akka.actor.ActorSystem;
import akka.actor.CoordinatedShutdown;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import luj.cluster.api.node.NodeShutdownListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShutdownListenRegister {

  public ShutdownListenRegister(List<NodeShutdownListener> shutdownList, ActorSystem system,
      Object startParam) {
    _shutdownList = shutdownList;
    _system = system;
    _startParam = startParam;
  }

  public void register() {
    CoordinatedShutdown.get(_system).addTask(CoordinatedShutdown
        .PhaseBeforeActorSystemTerminate(), "lujcluster-listen", this::triggerListeners);
  }

  private CompletionStage<Done> triggerListeners() {
    ShutdownContextImpl ctx = new ShutdownContextImpl();
    ctx._startParam = _startParam;

    for (NodeShutdownListener listener : _shutdownList) {
      try {
        listener.onShutdown(ctx);
      } catch (Throwable t) {
        LOG.error(t.getMessage(), t);
      }
    }

    return CompletableFuture.completedFuture(Done.getInstance());
  }

  private static final Logger LOG = LoggerFactory.getLogger(ShutdownListenRegister.class);

  private final List<NodeShutdownListener> _shutdownList;

  private final ActorSystem _system;
  private final Object _startParam;
}
