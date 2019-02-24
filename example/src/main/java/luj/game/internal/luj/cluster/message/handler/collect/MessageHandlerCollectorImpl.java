package luj.game.internal.luj.cluster.message.handler.collect;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.generic.GenericType;
import luj.ava.stream.StreamX;
import luj.game.api.proto.GameProtoHandler;

final class MessageHandlerCollectorImpl implements MessageHandlerCollector {

  MessageHandlerCollectorImpl(List<GameProtoHandler<?>> protoHandlerList) {
    _protoHandlerList = protoHandlerList;
  }

  @Override
  public Map<String, ?> collect() {
    return StreamX.from(_protoHandlerList)
        .collect(Collectors.toMap(this::getMessageId, Function.identity()));
  }

  private String getMessageId(GameProtoHandler<?> handler) {
    GenericType handlerType = GenericType.of(handler.getClass().getGenericInterfaces()[0]);
    return handlerType.getTypeArg(0).getName();
  }

  private final List<GameProtoHandler<?>> _protoHandlerList;
}
