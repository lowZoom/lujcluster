package luj.cluster.internal.node.message.receive.message.remote;

/**
 * 将rpc收到的远程消息消化进akka
 */
public class NodeSendRemote2Msg {

  public NodeSendRemote2Msg(String messageKey, byte[] messageData, String senderHost,
      int senderPort) {
    _messageKey = messageKey;
    _messageData = messageData;
    _senderHost = senderHost;
    _senderPort = senderPort;
  }

  public String getMessageKey() {
    return _messageKey;
  }

  public byte[] getMessageData() {
    return _messageData;
  }

  public String getSenderHost() {
    return _senderHost;
  }

  public int getSenderPort() {
    return _senderPort;
  }

  private final String _messageKey;
  private final byte[] _messageData;

  private final String _senderHost;
  private final int _senderPort;
}
