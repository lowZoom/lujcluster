package luj.cluster.internal.node.message.serialize;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.ava.reflect.type.TypeX;
import luj.cluster.api.node.NodeMessageSerializer;

public class MessageSerializerCollector {

  public MessageSerializerCollector(List<NodeMessageSerializer<?>> serializerList) {
    _serializerList = serializerList;
  }

  public Map<String, NodeMessageSerializer<?>> collect() {
    return _serializerList.stream()
        .collect(Collectors.toMap(this::getTypeName, Function.identity()));
  }

  private String getTypeName(NodeMessageSerializer<?> serializer) {
    return TypeX.ofInstance(serializer)
        .getSupertype(NodeMessageSerializer.class)
        .getTypeParam(0)
        .asClass()
        .getName();
  }

  private final List<NodeMessageSerializer<?>> _serializerList;
}
