package luj.cluster.internal.node.message.serialize.invoke;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import luj.cluster.api.node.NodeMessageSerializer;

public enum MessageDeserializeInvoker {
  GET;

  @SuppressWarnings("unchecked")
  public Object invoke(String type, byte[] data,
      Map<String, NodeMessageSerializer<?>> serializerMap, Object applicationBean) {
    NodeMessageSerializer<?> serializer = serializerMap.get(type);
    if (serializer == null) {
      return useJavaSerial(data);
    }

    ContextImpl ctx = new ContextImpl();
    ctx._appBean = applicationBean;

    return ((NodeMessageSerializer<Object>) serializer).deserialize(ctx, data);
  }

  private Object useJavaSerial(byte[] data) {
    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(data);
        ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
      return objIn.readObject();

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
