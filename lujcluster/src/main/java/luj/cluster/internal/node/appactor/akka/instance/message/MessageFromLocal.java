package luj.cluster.internal.node.appactor.akka.instance.message;

import luj.cluster.api.actor.ActorMessageHandler;

public class MessageFromLocal {

  public MessageFromLocal(String msgKey, Object appMsg, ActorMessageHandler.Node remoteNode) {
    _msgKey = msgKey;
    _appMsg = appMsg;
    _remoteNode = remoteNode;
  }

  public String getMsgKey() {
    return _msgKey;
  }

  public Object getAppMsg() {
    return _appMsg;
  }

  public ActorMessageHandler.Node getRemoteNode() {
    return _remoteNode;
  }

  private final String _msgKey;
  private final Object _appMsg;

  private final ActorMessageHandler.Node _remoteNode;
}
