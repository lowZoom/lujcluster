package luj.cluster.internal.node.member.join.trigger2;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.health.model.HealthService;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import luj.cluster.api.node.NodeNewMemberListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinConsulTrigger {

  public JoinConsulTrigger(NodeNewMemberListener joinListener,
      Collection<HealthService.Service> serviceList, ActorRef receiveRef, ActorRef memberRef,
      Object clusterStartParam) {
    _joinListener = joinListener;
    _serviceList = serviceList;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
    _clusterStartParam = clusterStartParam;
  }

  public void trigger() {
    for (HealthService.Service service : _serviceList) {
      triggerOne(service);
    }
  }

  private void triggerOne(HealthService.Service service) {
    MemberContextImpl ctx = new MemberContextImpl();
    ctx._selfNode = makeSelfNode();
    ctx._memberNode = makeRemoteNode(service);
    ctx._applicationBean = _clusterStartParam;

    try {
      _joinListener.onMember(ctx);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private NodeSelfImpl makeSelfNode() {
    NodeSelfImpl node = new NodeSelfImpl();
    node._receiveRef = _receiveRef;
    node._memberRef = _memberRef;
    return node;
  }

  private NodeMemberImpl makeRemoteNode(HealthService.Service service) {
    NodeMemberImpl node = new NodeMemberImpl();
    node._tags = ImmutableSet.copyOf(service.getTags());

    node._memberRef = _memberRef;
    node._memberHost = service.getAddress();
    node._memberPort = service.getPort();

    return node;
  }

  private static final Logger LOG = LoggerFactory.getLogger(JoinConsulTrigger.class);

  private final NodeNewMemberListener _joinListener;
  private final Collection<HealthService.Service> _serviceList;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;

  private final Object _clusterStartParam;
}
