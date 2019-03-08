package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.AbstractActor;
import akka.actor.Props;

public class AppAktor extends AbstractActor {

  public static Props props(Object state) {
    return Props.create(AppAktor.class, () -> new AppAktor(state));
  }

  AppAktor(Object state) {
    _state = state;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()

        .build();
  }

  private final Object _state;
}
