package luj.cluster.internal.node.message.serialize;

import akka.serialization.JSerializer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import luj.cluster.api.node.NodeMessageSerializer;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.serialize.invoke.MessageDeserializeInvoker;
import luj.cluster.internal.node.message.serialize.invoke.MessageSerializeInvoker;

public class AkkaMessageSerializer extends JSerializer {

  public static Map<String, NodeMessageSerializer<?>> sSerializerMap;
  public static Object sApplicationBean;

  @Override
  public int identifier() {
    return 1001;
  }

  @Override
  public boolean includeManifest() {
    return false;
  }

  //TODO: 后面改成用protobuf

  @Override
  public byte[] toBinary(Object o) {
    NodeSendRemoteMsg msg = (NodeSendRemoteMsg) o;

    try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream objOut = new ObjectOutputStream(byteOut)) {
      objOut.writeObject(msg.getMessageKey());

      objOut.writeObject(MessageSerializeInvoker.GET
          .invoke(msg.getMessage(), sSerializerMap, sApplicationBean));

      objOut.flush();
      return byteOut.toByteArray();

    } catch (IOException e) {
      throw new UnsupportedOperationException(e);
    }
  }

  @Override
  public Object fromBinaryJava(byte[] bytes, Class<?> manifest) {
    try (ByteArrayInputStream byteIn = new ByteArrayInputStream(bytes);
        ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
      String msgKey = (String) objIn.readObject();

      String msgType = (String) objIn.readObject();
      byte[] msgData = (byte[]) objIn.readObject();

      Object msgObj = MessageDeserializeInvoker.GET
          .invoke(msgType, msgData, sSerializerMap, sApplicationBean);

      return new NodeSendRemoteMsg(msgKey, msgObj);

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
