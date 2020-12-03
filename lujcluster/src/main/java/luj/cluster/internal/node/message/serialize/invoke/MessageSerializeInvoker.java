package luj.cluster.internal.node.message.serialize.invoke;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import luj.cluster.api.node.message.MessageValueResolver;
import luj.cluster.api.node.message.NodeMessageSerializer;

public enum MessageSerializeInvoker {
  GET;

  public byte[] invoke(Object msg, MessageValueResolver msgResolver,
      Map<String, NodeMessageSerializer<?>> serializerMap, Object applicationBean)
      throws IOException {
    Object msgVal = (msgResolver == null) ? msg : msgResolver.resolve(msg);
    String msgType = msgVal.getClass().getName();

    //TODO: 后面改用protobuf
    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut)) {
      objOut.writeObject(msgType);

      NodeMessageSerializer<?> serializer = serializerMap.get(msgType);
      writeObject(objOut, msgVal, serializer, applicationBean);

      objOut.flush();
      return byteOut.toByteArray();
    }
  }

  @SuppressWarnings("unchecked")
  private void writeObject(ObjectOutputStream objOut, Object msgVal,
      NodeMessageSerializer<?> serializer, Object applicationBean) throws IOException {
    if (serializer == null) {
      objOut.writeObject(asSerializable(msgVal));
      return;
    }

    ContextImpl ctx = new ContextImpl();
    ctx._appBean = applicationBean;

    byte[] msgData = ((NodeMessageSerializer<Object>) serializer).serialize(ctx, msgVal);
    objOut.writeObject(msgData);
  }

  private Serializable asSerializable(Object obj) {
    return (Serializable) obj;
  }
}
