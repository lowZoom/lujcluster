package luj.cluster.internal.node.message.receive.message.remote;

public class NodeSendRemoteMsg {

  public NodeSendRemoteMsg(String messageKey, Object message) {
    _messageKey = messageKey;
    _message = message;
  }

  public String getMessageKey() {
    return _messageKey;
  }

  public Object getMessage() {
    return _message;
  }

  private final String _messageKey;

  private final Object _message;
}
