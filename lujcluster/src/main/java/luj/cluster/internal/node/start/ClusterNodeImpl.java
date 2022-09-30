package luj.cluster.internal.node.start;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.shutdown.CoordShutdownRunner;

final class ClusterNodeImpl implements ClusterNode {

  @Override
  public void sendMessage(String msgKey, Object msg, ActorRef sender) {
    _receiveRef.tell(new NodeSendRemoteMsg(msgKey, msg), sender);
  }

  @Override
  public void shutdown() {
    new CoordShutdownRunner(_actorSystem).run();
  }

  void setReceiveRef(ActorRef receiveRef) {
    _receiveRef = receiveRef;
  }

  public void setActorSystem(ActorSystem actorSystem) {
    _actorSystem = actorSystem;
  }

  private ActorRef _receiveRef;

  private ActorSystem _actorSystem;
}
