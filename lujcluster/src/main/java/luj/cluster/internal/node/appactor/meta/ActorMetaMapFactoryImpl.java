package luj.cluster.internal.node.appactor.meta;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

enum ActorMetaMapFactoryImpl {
  SINGLETON;

  ActorMetaMap create(ClusterBeanCollector.Result beanCollect) {
    Map<Class<?>, ActorPreStartHandler<?>> prestartMap = beanCollect
        .getActorPreStartHandlers().stream()
        .collect(toMap(h -> getActorType(h, ActorPreStartHandler.class), Function.identity()));

    Map<Class<?>, ActorPostStopHandler<?>> poststopMap = beanCollect
        .getActorPostStopHandlers().stream()
        .collect(toMap(h -> getActorType(h, ActorPostStopHandler.class), Function.identity()));

    Map<Class<?>, List<ActorMessageHandler<?, ?>>> messageHandleMap = beanCollect
        .getActorMessageHandlers().stream()
        .collect(groupingBy(h -> getMsgHandleParam(h, 0)));

    return new ActorMetaMapImpl(ImmutableSet.<Class<?>>builder()
        .addAll(prestartMap.keySet())
        .addAll(messageHandleMap.keySet())
        .build().stream()
        .collect(toMap(Function.identity(), k -> createMeta(prestartMap.get(k),
            poststopMap.get(k), messageHandleMap.getOrDefault(k, ImmutableList.of())))));
  }

  private Class<?> getActorType(Object handler, Class<?> handlerType) {
    return TypeX.ofInstance(handler)
        .getSupertype(handlerType)
        .getTypeParam(0)
        .asClass();
  }

  private ActorMeta createMeta(ActorPreStartHandler<?> prestartHandler,
      ActorPostStopHandler<?> postStopHandler, List<ActorMessageHandler<?, ?>> msgHandlerList) {
    MessageHandleMapImpl handleMap = new MessageHandleMapImpl();
    handleMap._handlerMap = msgHandlerList.stream()
        .collect(toMap(h -> getMsgHandleParam(h, 1).getName(), Function.identity()));

    var meta = new ActorMetaImpl();
    meta._preStartHandler = prestartHandler;
    meta._postStopHandler = postStopHandler;
    meta._actorMessageHandleMap = handleMap;

    return meta;
  }

  private Class<?> getMsgHandleParam(ActorMessageHandler<?, ?> handler, int index) {
    return TypeX.ofInstance(handler)
        .getSupertype(ActorMessageHandler.class)
        .getTypeParam(index)
        .asClass();
  }
}
