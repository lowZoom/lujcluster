package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.AbstractActor;
import akka.actor.Props;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.poststop.AppPoststopHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.prestart.AppPrestartHandleInvoker;
import luj.cluster.internal.node.appactor.meta.ActorMeta;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;

public final class AppAktor extends AbstractActor {

  public static Props props(Object state, ActorMeta meta, ActorMetaMap metaMap) {
    return Props.create(AppAktor.class, () -> new AppAktor(state, meta, metaMap));
  }

  AppAktor(Object state, ActorMeta meta, ActorMetaMap actorMetaMap) {
    _state = state;
    _meta = meta;

    _actorMetaMap = actorMetaMap;
  }

  @Override
  public void preStart() throws Exception {
    new AppPrestartHandleInvoker(this).invoke();
  }

  @Override
  public void postStop() throws Exception {
    new AppPoststopHandleInvoker(this).invoke();
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

  public ActorMetaMap getActorMetaMap() {
    return _actorMetaMap;
  }

  private void onMessage(Object msg) {
    AppMessageHandleInvoker.Factory.create(this, msg, getSender()).invoke();
  }

  private final Object _state;
  private final ActorMeta _meta;

  private final ActorMetaMap _actorMetaMap;
}
