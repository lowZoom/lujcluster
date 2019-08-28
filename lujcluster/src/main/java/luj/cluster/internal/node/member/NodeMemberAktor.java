package luj.cluster.internal.node.member;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import luj.cluster.api.node.NodeNewMemberListener;

public class NodeMemberAktor extends AbstractActor {

  public static Props props(Cluster cluster, NodeNewMemberListener joinListener) {
    return Props.create(NodeMemberAktor.class, () -> new NodeMemberAktor(cluster, joinListener));
  }

  public NodeMemberAktor(Cluster cluster, NodeNewMemberListener joinListener) {
    _cluster = cluster;
    _joinListener = joinListener;
  }

  @Override
  public void preStart() {
    _cluster.subscribe(self(), ClusterEvent.initialStateAsEvents(), ClusterEvent.MemberUp.class);
  }

  @Override
  public void postStop() {
    _cluster.unsubscribe(self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(ClusterEvent.MemberUp.class, new OnMemberUp(this, _joinListener))
        .build();
  }

  private final Cluster _cluster;

  private final NodeNewMemberListener _joinListener;
}
