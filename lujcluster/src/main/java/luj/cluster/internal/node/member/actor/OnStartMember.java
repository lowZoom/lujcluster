package luj.cluster.internal.node.member.actor;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import akka.cluster.Cluster;
import akka.cluster.ClusterEvent;
import akka.japi.pf.FI;
import java.util.Queue;
import luj.cluster.internal.node.consul.start.NodeConsulStarter;
import luj.cluster.internal.node.member.message.StartMemberMsg;
import luj.cluster.internal.node.member.receive.NodeSendItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnStartMember implements FI.UnitApply<StartMemberMsg> {

  OnStartMember(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(StartMemberMsg i) throws Exception {
    ActorRef receiveRef = i.getReceiveRef();
    _aktor.setReceiveRef(receiveRef);

    new NodeConsulStarter(i.getNodeConfig(), _aktor.getJoinListener(),
        _aktor.getHealthListener(), receiveRef, _aktor.self()).start();

    if (!i.isClusterEnabled()) {
      return;
    }

    consumeReceiveBuffer(receiveRef);

    ActorContext context = _aktor.context();
    Cluster cluster = Cluster.get(context.system());
    _aktor.setCluster(cluster);

    //TODO: 把实际加入集群推迟到这里（用代码连接种子节点）

    LOG.debug("[cluster]监听集群事件...");
    cluster.subscribe(_aktor.self(), (ClusterEvent.SubscriptionInitialStateMode)
        ClusterEvent.initialStateAsEvents(), ClusterEvent.MemberUp.class);
  }

  private void consumeReceiveBuffer(ActorRef receiveRef) {
    Queue<NodeSendItem> queue = _aktor.getReceiveQueue();

    while (!queue.isEmpty()) {
      NodeSendItem item = queue.poll();
      receiveRef.tell(item.getMessage(), item.getSender());
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnStartMember.class);

  private final NodeMemberAktor _aktor;
}
