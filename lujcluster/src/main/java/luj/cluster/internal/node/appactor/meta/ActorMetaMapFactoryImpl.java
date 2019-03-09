package luj.cluster.internal.node.appactor.meta;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.generic.GenericType;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

enum ActorMetaMapFactoryImpl {
  SINGLETON;

  ActorMetaMap create(ClusterBeanCollector.Result beanCollect) {
    return new ActorMetaMapImpl(beanCollect.getActorMessageHandlers().stream()
        .collect(Collectors.groupingBy(this::getActorType))
        .entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> createMeta(e.getValue()))));
  }

  private Class<?> getActorType(ActorMessageHandler<?, ?> handler) {
    return GenericType.of(handler.getClass()
        .getInterfaces()[0]
        .getGenericInterfaces()[0])
        .getTypeArg(0);
  }

  private ActorMeta createMeta(List<ActorMessageHandler<?, ?>> handlerList) {
    MessageHandleMapImpl handleMap = new MessageHandleMapImpl(handlerList.stream()
        .collect(Collectors.toMap(this::getMessageType, Function.identity())));

    return new ActorMetaImpl(handleMap);
  }

  private Class<?> getMessageType(ActorMessageHandler<?, ?> handler) {
    return GenericType.of(handler.getClass().getGenericInterfaces()[0]).getTypeArg(0);
  }
}
