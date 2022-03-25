package luj.cluster.internal.node.member.actor.message;

/**
 * 将本地消息请求转化为grpc调用
 */
public class MemberSendRpcMsg {

  public MemberSendRpcMsg(String messageKey, Object message, String targetHost, int targetPort) {
    _messageKey = messageKey;
    _message = message;
    _targetHost = targetHost;
    _targetPort = targetPort;
  }

  public String getMessageKey() {
    return _messageKey;
  }

  public Object getMessage() {
    return _message;
  }

  public String getTargetHost() {
    return _targetHost;
  }

  public int getTargetPort() {
    return _targetPort;
  }

  private final String _messageKey;
  private final Object _message;

  private final String _targetHost;
  private final int _targetPort;
}
