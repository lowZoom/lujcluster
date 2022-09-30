package luj.cluster.internal.node.appactor.akka.instance;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import java.util.Map;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.handle.message.local.AppMessageHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.poststop.AppPoststopHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.handle.prestart.AppPrestartHandleInvoker;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromLocal;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;
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
    builder.match(MessageFromRemote.class, new OnMessageFromRemote(this));
    builder.match(MessageFromLocal.class, new OnMessageFromLocal(this));

    for (Map.Entry<String, ActorMessageHandler<?, ?>> e : _meta.getMessageHandleMap()) {
      ActorMessageHandler<?, ?> handler = e.getValue();
      builder.match(loadClass(e.getKey()), m -> onMessage(m, handler));
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

  private Class<?> loadClass(String name) {
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private void onMessage(Object msg, ActorMessageHandler<?, ?> handler) {
    AppMessageHandleInvoker.GET.invoke(this, handler, msg);
  }

  private static final Logger LOG = LoggerFactory.getLogger(AppAktor.class);

  private final Object _state;
  private final ActorMeta _meta;

  private final ActorMetaMap _actorMetaMap;
  private final ActorRef _clusterMemberRef;
}
