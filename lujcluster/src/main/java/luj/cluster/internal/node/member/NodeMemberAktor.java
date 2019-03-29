package luj.cluster.internal.node.member;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;

public class NodeMemberAktor extends AbstractActor {

  public static Props props(Cluster cluster) {
    return Props.create(NodeMemberAktor.class, () -> new NodeMemberAktor(cluster));
  }

  public NodeMemberAktor(Cluster cluster) {
    _cluster = cluster;
  }

  @Override
  public void preStart() throws Exception {
    _cluster.subscribe(self(), ClusterEvent.initialStateAsEvents(), ClusterEvent.MemberUp.class);
  }

  @Override
  public void postStop() throws Exception {
    _cluster.unsubscribe(self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(ClusterEvent.MemberUp.class, new OnMemberUp(this))
        .build();
  }

  private final Cluster _cluster;
}
