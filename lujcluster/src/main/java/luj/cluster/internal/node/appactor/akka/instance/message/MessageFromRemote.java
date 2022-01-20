package luj.cluster.internal.node.appactor.akka.instance.message;

public class MessageFromRemote {

  public MessageFromRemote(String msgKey, Object msg, String senderHost, int senderPort) {
    _msgKey = msgKey;
    _msg = msg;
    _senderHost = senderHost;
    _senderPort = senderPort;
  }

  public String getMsgKey() {
    return _msgKey;
  }

  public Object getMsg() {
    return _msg;
  }

  public String getSenderHost() {
    return _senderHost;
  }

  public int getSenderPort() {
    return _senderPort;
  }

  private final String _msgKey;
  private final Object _msg;

  private final String _senderHost;
  private final int _senderPort;
}
