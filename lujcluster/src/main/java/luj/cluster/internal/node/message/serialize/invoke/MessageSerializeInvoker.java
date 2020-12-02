package luj.cluster.internal.node.message.serialize.invoke;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import luj.cluster.api.node.NodeMessageSerializer;

public enum MessageSerializeInvoker {
  GET;

  //TODO: 后面改用protobuf

  @SuppressWarnings("unchecked")
  public byte[] invoke(Object msg, Map<String, NodeMessageSerializer<?>> serializerMap,
      Object applicationBean) {
    String msgType = msg.getClass().getName();

    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut)) {
      objOut.writeObject(msgType);

      NodeMessageSerializer<?> serializer = serializerMap.get(msgType);
      if (serializer == null) {
        return useJavaSerial(byteOut, objOut, (Serializable) msg);
      }

      ContextImpl ctx = new ContextImpl();
      ctx._appBean = applicationBean;
      return ((NodeMessageSerializer<Object>) serializer).serialize(ctx, msg);

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  private byte[] useJavaSerial(ByteArrayOutputStream byteOut, ObjectOutputStream objOut,
      Serializable msg) throws IOException {
    objOut.writeObject(msg);
    objOut.flush();
    return byteOut.toByteArray();
  }
}
