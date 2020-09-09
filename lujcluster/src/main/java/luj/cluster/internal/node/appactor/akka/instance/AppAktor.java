package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.poststop.AppPoststopHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.prestart.AppPrestartHandleInvoker;
import luj.cluster.internal.node.appactor.meta.ActorMeta;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class AppAktor extends AbstractActor {

  public static Props props(Object state, ActorMeta meta, ActorMetaMap metaMap,
      ActorRef clusterMemberRef) {
    return Props.create(AppAktor.class, () -> new AppAktor(state, meta, metaMap, clusterMemberRef));
  }

  AppAktor(Object state, ActorMeta meta, ActorMetaMap actorMetaMap, ActorRef clusterMemberRef) {
    _state = state;
    _meta = meta;

    _actorMetaMap = actorMetaMap;
    _clusterMemberRef = clusterMemberRef;
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
    ReceiveBuilder builder = receiveBuilder();
    for (Map.Entry<Class<?>, ActorMessageHandler<?, ?>> e : _meta.getMessageHandleMap()) {
      ActorMessageHandler<?, ?> handler = e.getValue();
      builder.match(e.getKey(), m -> onMessage(m, handler));
    }
    return builder.build();
  }

  @Override
  public void unhandled(Object message) {
    LOG.warn("未处理的消息：{} @ {}", message.getClass().getName(), _state.getClass().getName());
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

  public ActorRef getClusterMemberRef() {
    return _clusterMemberRef;
  }

  private void onMessage(Object msg, ActorMessageHandler<?, ?> handler) {
    new AppMessageHandleInvoker(this, handler, msg, getSender(), _clusterMemberRef).invoke();
  }

  private static final Logger LOG = LoggerFactory.getLogger(AppAktor.class);

  private final Object _state;
  private final ActorMeta _meta;

  private final ActorMetaMap _actorMetaMap;
  private final ActorRef _clusterMemberRef;
}
