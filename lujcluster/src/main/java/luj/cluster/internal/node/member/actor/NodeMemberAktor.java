package luj.cluster.internal.node.member.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.api.node.message.MessageValueResolver;
import luj.cluster.api.node.message.NodeMessageSerializer;
import luj.cluster.internal.node.member.message.LeaveAndShutdownMsg;
import luj.cluster.internal.node.member.message.MemberSendRpcMsg;
import luj.cluster.internal.node.member.message.StartMemberMsg;
import luj.cluster.internal.node.member.receive.NodeSendItem;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class NodeMemberAktor extends AbstractActor {

  public static Props props(Map<String, NodeMessageSerializer<?>> msgCodecMap,
      ClusterBeanCollector.Result beanCollect, ClusterNodeStarter.Config nodeConfig) {
    MessageValueResolver msgTypeResolver = beanCollect.getMessageTypeResolver();
    NodeNewMemberListener nodeJoinListener = beanCollect.getNodeJoinListener();

    return Props.create(NodeMemberAktor.class, () -> new NodeMemberAktor(
        msgCodecMap, msgTypeResolver, nodeJoinListener, nodeConfig.startParam(),
        nodeConfig.selfHost(), nodeConfig.selfPort()));
  }

  NodeMemberAktor(Map<String, NodeMessageSerializer<?>> msgCodecMap,
      MessageValueResolver msgTypeResolver, NodeNewMemberListener joinListener,
      Object applicationBean, String selfHost, int selfPort) {
    _msgCodecMap = msgCodecMap;
    _msgTypeResolver = msgTypeResolver;
    _joinListener = joinListener;
    _applicationBean = applicationBean;
    _selfHost = selfHost;
    _selfPort = selfPort;
  }

  @Override
  public void postStop() {
    if (_cluster == null) {
      return;
    }

    _cluster.unsubscribe(self());
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(ClusterEvent.MemberUp.class, new OnClusterMemberUp(this))
        .match(StartMemberMsg.class, new OnStartMember(this))
        .match(LeaveAndShutdownMsg.class, new OnLeaveAndShutdown(this))
        .match(MemberSendRpcMsg.class, new OnMemberSendRpc(this)) // 发送
        .match(NodeSendRemoteMsg.class, new OnNodeSendRemote(this)) // 接收
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

  public Queue<NodeSendItem> getReceiveQueue() {
    return _receiveQueue;
  }

  public Map<String, ManagedChannel> getRpcChannelMap() {
    return _rpcChannelMap;
  }

  public NodeNewMemberListener getJoinListener() {
    return _joinListener;
  }

  public Map<String, NodeMessageSerializer<?>> getMsgCodecMap() {
    return _msgCodecMap;
  }

  public MessageValueResolver getMsgTypeResolver() {
    return _msgTypeResolver;
  }

  public Object getApplicationBean() {
    return _applicationBean;
  }

  public String getSelfHost() {
    return _selfHost;
  }

  public int getSelfPort() {
    return _selfPort;
  }

  private Cluster _cluster;

  /**
   * @see luj.cluster.internal.node.message.receive.actor.NodeReceiveAktor
   */
  private ActorRef _receiveRef;

  private final Queue<NodeSendItem> _receiveQueue = new LinkedList<>();
  private final Map<String, ManagedChannel> _rpcChannelMap = new HashMap<>();

  private final Map<String, NodeMessageSerializer<?>> _msgCodecMap;
  private final MessageValueResolver _msgTypeResolver;

  private final NodeNewMemberListener _joinListener;
  private final Object _applicationBean;

  private final String _selfHost;
  private final int _selfPort;
}
