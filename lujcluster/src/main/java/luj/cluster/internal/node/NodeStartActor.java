package luj.cluster.internal.node;

import akka.actor.AbstractActor;

final class NodeStartActor extends AbstractActor {

  @Override
  public Receive createReceive() {
    return receiveBuilder().build();
  }

  @Override
  public void preStart() {
    System.out.println("akka启动");
  }
}
