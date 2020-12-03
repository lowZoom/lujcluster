package luj.cluster.internal.node.message.serialize.invoke;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import luj.cluster.api.node.message.NodeMessageSerializer;

public enum MessageDeserializeInvoker {
  GET;

  public Object invoke(byte[] bytes,
      Map<String, NodeMessageSerializer<?>> serializerMap, Object applicationBean)
      throws Exception {
    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(byteIn)) {

      String msgType = (String) objIn.readObject();
      NodeMessageSerializer<?> serializer = serializerMap.get(msgType);

      if (serializer == null) {
        return objIn.readObject();
      }

      ContextImpl ctx = new ContextImpl();
      ctx._appBean = applicationBean;

      byte[] msgData = (byte[]) objIn.readObject();
      return serializer.deserialize(ctx, msgData);
    }
  }
}
