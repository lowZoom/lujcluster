package luj.cluster.internal.node.appactor.akka.instance.handle.prestart;

import akka.actor.ActorSystem;
import akka.actor.CoordinatedShutdown;
import luj.cluster.api.actor.ActorPreStartHandler;

final class SystemImpl implements ActorPreStartHandler.System {

  @Override
  public void shutdown() {
    CoordinatedShutdown.get(_system).runAll();
  }

  ActorSystem _system;
}
