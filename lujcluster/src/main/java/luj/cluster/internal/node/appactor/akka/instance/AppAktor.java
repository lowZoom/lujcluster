package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.AbstractActor;
import akka.actor.Props;
import luj.cluster.internal.node.appactor.akka.instance.handle.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.meta.ActorMeta;

public class AppAktor extends AbstractActor {

  public static Props props(Object state, ActorMeta meta) {
    return Props.create(AppAktor.class, () -> new AppAktor(state, meta));
  }

  AppAktor(Object state, ActorMeta meta) {
    _state = state;
    _meta = meta;
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .matchAny(this::onMessage)
        .build();
  }

  public Object getState() {
    return _state;
  }

  public ActorMeta getMeta() {
    return _meta;
  }

  private void onMessage(Object msg) {
    AppMessageHandleInvoker.Factory.create(this, msg).invoke();
  }

  private final Object _state;

  private final ActorMeta _meta;
}
