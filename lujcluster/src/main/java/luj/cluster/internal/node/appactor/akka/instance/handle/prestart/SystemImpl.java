package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorSystem;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.node.shutdown.CoordShutdownRunner;

final class SystemImpl implements ActorPreStartHandler.System {

  @Override
  public void shutdown() {
    new CoordShutdownRunner(_system).run();
  }

  ActorSystem _system;
}
