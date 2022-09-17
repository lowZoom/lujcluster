package luj.cluster.internal.node.shutdown;

import akka.actor.Actor;
import akka.actor.ActorSystem;
import akka.actor.CoordinatedShutdown;

public class CoordShutdownRunner {

  public static CoordShutdownRunner get(Actor akkaActor) {
    return new CoordShutdownRunner(akkaActor.context().system());
  }

  public CoordShutdownRunner(ActorSystem system) {
    _system = system;
  }

  public void run() {
    CoordinatedShutdown.get(_system).runAll();
  }

  private final ActorSystem _system;
}
