package luj.cluster.internal.node.member.actor;

import akka.cluster.ClusterEvent;
import akka.japi.pf.FI;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.member.join.trigger.MemberJoinTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnClusterMemberUp implements FI.UnitApply<ClusterEvent.MemberUp> {

  OnClusterMemberUp(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  /**
   * @see luj.cluster.internal.node.start.actor.PreStart#run
   */
  @Override
  public void apply(ClusterEvent.MemberUp i) {
    LOG.debug("[cluster]新节点加入：{}", i);

    NodeNewMemberListener joinListener = _aktor.getJoinListener();
    if (joinListener == null) {
      LOG.info("[cluster]没有监听新节点事件（NodeNewMemberListener），事件被忽略");
      return;
    }

    new MemberJoinTrigger(i, _aktor, joinListener, _aktor.getApplicationBean()).trigger();
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnClusterMemberUp.class);

  private final NodeMemberAktor _aktor;
}
