package luj.cluster.internal.node.appactor.meta;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPreSstartHandler;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

enum ActorMetaMapFactoryImpl {
  SINGLETON;

  ActorMetaMap create(ClusterBeanCollector.Result beanCollect) {
    Map<Class<?>, ActorPreSstartHandler<?>> prestartMap = beanCollect
        .getActorPreStartHandlers().stream()
        .collect(Collectors.toMap(this::getActorType, Function.identity()));

    return new ActorMetaMapImpl(beanCollect.getActorMessageHandlers().stream()
        .collect(Collectors.groupingBy(h -> getMsgHandleParam(h, 0)))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e ->
            createMeta(prestartMap.get(e.getKey()), e.getValue()))));
  }

  private Class<?> getActorType(ActorPreSstartHandler<?> handler) {
    return TypeX.ofInstance(handler)
        .getSupertype(ActorPreSstartHandler.class)
        .getTypeParam(0)
        .asClass();
  }

  private ActorMeta createMeta(ActorPreSstartHandler<?> prestartHandler,
      List<ActorMessageHandler<?, ?>> msgHandlerList) {
    MessageHandleMapImpl handleMap = new MessageHandleMapImpl(msgHandlerList.stream()
        .collect(Collectors.toMap(h -> getMsgHandleParam(h, 1), Function.identity())));

    return new ActorMetaImpl(prestartHandler, handleMap);
  }

  private Class<?> getMsgHandleParam(ActorMessageHandler<?, ?> handler, int index) {
    return TypeX.ofInstance(handler)
        .getSupertype(ActorMessageHandler.class)
        .getTypeParam(index)
        .asClass();
  }
}
