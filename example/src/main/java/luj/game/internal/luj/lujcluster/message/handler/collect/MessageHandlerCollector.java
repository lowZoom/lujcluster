package luj.game.internal.luj.lujcluster.message.handler.collect;

import java.util.List;
import java.util.Map;
import luj.game.api.proto.GameProtoHandler;

public interface MessageHandlerCollector {

  interface Factory {

    static MessageHandlerCollector create(List<GameProtoHandler<?>> protoHandlerList) {
      return new MessageHandlerCollectorImpl(protoHandlerList);
    }
  }

  Map<String, ?> collect();
}
