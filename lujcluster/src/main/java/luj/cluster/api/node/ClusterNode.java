package luj.cluster.api.node;

import akka.actor.ActorRef;

public interface ClusterNode {

  void sendMessage(String msgKey, Object msg, ActorRef sender);

  void shutdown();
}
