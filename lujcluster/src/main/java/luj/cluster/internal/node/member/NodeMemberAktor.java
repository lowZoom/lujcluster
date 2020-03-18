package luj.cluster.internal.node.member;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

public class NodeMemberAktor extends AbstractActor {

  public static Props props(Cluster cluster, NodeNewMemberListener joinListener,
      ActorRef receiveRef, Object applicationBean) {
    return Props.create(NodeMemberAktor.class, () ->
        new NodeMemberAktor(cluster, joinListener, receiveRef, applicationBean));
  }

  public NodeMemberAktor(Cluster cluster, NodeNewMemberListener joinListener,
      ActorRef receiveRef, Object applicationBean) {
    _cluster = cluster;
    _joinListener = joinListener;
    _receiveRef = receiveRef;
    _applicationBean = applicationBean;
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
        .match(ClusterEvent.MemberUp.class, new OnMemberUp(this, _joinListener, _applicationBean))
        .match(NodeSendRemoteMsg.class, m -> _receiveRef.forward(m, context()))
        .build();
  }

  private final Cluster _cluster;
  private final NodeNewMemberListener _joinListener;

  /**
   * @see luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor
   */
  private final ActorRef _receiveRef;

  private final Object _applicationBean;
}
