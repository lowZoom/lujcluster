package luj.cluster.internal.node.member.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import java.util.LinkedList;
import java.util.Queue;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.member.message.LeaveAndShutdownMsg;
import luj.cluster.internal.node.member.message.StartMemberMsg;
import luj.cluster.internal.node.member.receive.NodeSendItem;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;

public class NodeMemberAktor extends AbstractActor {

  public static Props props(NodeNewMemberListener joinListener, Object applicationBean) {
    return Props.create(NodeMemberAktor.class, () ->
        new NodeMemberAktor(joinListener, applicationBean, new LinkedList<>()));
  }

  public NodeMemberAktor(NodeNewMemberListener joinListener, Object applicationBean,
      Queue<NodeSendItem> receiveQueue) {
    _joinListener = joinListener;
    _applicationBean = applicationBean;
    _receiveQueue = receiveQueue;
  }

  @Override
  public void postStop() {
    _cluster.unsubscribe(self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(ClusterEvent.MemberUp.class, new OnClusterMemberUp(this))
        .match(StartMemberMsg.class, new OnStartMember(this))
        .match(LeaveAndShutdownMsg.class, new OnLeaveAndShutdown(this))
        .match(NodeSendRemoteMsg.class, new OnNodeSendRemote(this))
        .build();
  }

  public Cluster getCluster() {
    return _cluster;
  }

  public void setCluster(Cluster cluster) {
    _cluster = cluster;
  }

  public ActorRef getReceiveRef() {
    return _receiveRef;
  }

  public void setReceiveRef(ActorRef receiveRef) {
    _receiveRef = receiveRef;
  }

  public NodeNewMemberListener getJoinListener() {
    return _joinListener;
  }

  public Object getApplicationBean() {
    return _applicationBean;
  }

  public Queue<NodeSendItem> getReceiveQueue() {
    return _receiveQueue;
  }

  private Cluster _cluster;

  /**
   * @see luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor
   */
  private ActorRef _receiveRef;

  private final NodeNewMemberListener _joinListener;
  private final Object _applicationBean;

  private final Queue<NodeSendItem> _receiveQueue;
}
