package luj.cluster.internal.node.message.send.message;

public final class NodeSendStartMsg {

  public NodeSendStartMsg(String messageId, Object message) {
    _messageId = messageId;
    _message = message;
  }

  public String getMessageId() {
    return _messageId;
  }

  public Object getMessage() {
    return _message;
  }

  private final String _messageId;

  private final Object _message;
}
