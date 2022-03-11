package luj.cluster.internal.node.appactor.message.handle;

import static java.util.stream.Collectors.groupingBy;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import java.util.stream.Collectors;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.actor.ActorMessageHandler;

public class ActorMessageHandleMapV2Factory {

  public ActorMessageHandleMapV2Factory(List<ActorMessageHandler<?, ?>> handlerList) {
    _handlerList = handlerList;
  }

  public ActorMessageHandleMapV2 create() {
    Multimap<String, Class<?>> resultMap = ArrayListMultimap.create();

    _handlerList.stream()
        .collect(groupingBy(h -> getMsgHandleParam(h, 1).getName()))
        .forEach((k, v) -> resultMap.putAll(k, getActorType(v)));

    ActorMessageHandleMapV2Impl mapImpl = new ActorMessageHandleMapV2Impl();
    mapImpl._handleMap = resultMap;
    return mapImpl;
  }

  private Class<?> getMsgHandleParam(ActorMessageHandler<?, ?> handler, int index) {
    return TypeX.ofInstance(handler)
        .getSupertype(ActorMessageHandler.class)
        .getTypeParam(index)
        .asClass();
  }

  private List<Class<?>> getActorType(List<ActorMessageHandler<?, ?>> handlerList) {
    return handlerList.stream()
        .map(h -> getMsgHandleParam(h, 0))
        .collect(Collectors.toList());
  }

  private final List<ActorMessageHandler<?, ?>> _handlerList;
}
