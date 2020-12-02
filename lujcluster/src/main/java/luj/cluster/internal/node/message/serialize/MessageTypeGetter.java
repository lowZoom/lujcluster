package luj.cluster.internal.node.message.serialize;

import luj.ava.reflect.type.TypeX;
import luj.cluster.api.node.NodeMessageSerializer;

public enum MessageTypeGetter {
  GET;

  public Class<?> getType(NodeMessageSerializer<?> serializer) {
    return TypeX.ofInstance(serializer)
        .getSupertype(NodeMessageSerializer.class)
        .getTypeParam(0)
        .asClass();
  }
}
