package luj.cluster.internal.node.member.join.trigger2;

import akka.actor.ActorRef;
import com.google.common.collect.ImmutableSet;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinConsulOneTrigger {

  public JoinConsulOneTrigger(NodeNewMemberListener joinListener, RpcNodeJoinMsg service,
      ActorRef receiveRef, ActorRef memberRef, Object clusterStartParam) {
    _joinListener = joinListener;
    _service = service;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
    _clusterStartParam = clusterStartParam;
  }

  public void trigger() {
    NodeMemberImpl memberNode = makeRemoteNode(_service);
    if (_joinListener == null) {
      printMemberNode(memberNode);
      return;
    }

    MemberContextImpl ctx = new MemberContextImpl();
    ctx._selfNode = makeSelfNode();
    ctx._memberNode = memberNode;
    ctx._applicationBean = _clusterStartParam;

    try {
      _joinListener.onMember(ctx);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private void printMemberNode(NodeMemberImpl node) {
    LOG.debug("[cluster]新节点加入，没有处理器，name：{}，tag：{}", node.getName(), node.getTags());
  }

  private NodeSelfImpl makeSelfNode() {
    NodeSelfImpl node = new NodeSelfImpl();
    node._receiveRef = _receiveRef;
    node._memberRef = _memberRef;
    return node;
  }

  private NodeMemberImpl makeRemoteNode(RpcNodeJoinMsg service) {
    NodeMemberImpl node = new NodeMemberImpl();
    node._name = service.getName();
    node._tags = ImmutableSet.copyOf(service.getTagList());

    node._memberRef = _memberRef;
    node._memberHost = service.getHost();
    node._memberPort = service.getPort();

    return node;
  }

  private static final Logger LOG = LoggerFactory.getLogger(JoinConsulOneTrigger.class);

  private final NodeNewMemberListener _joinListener;
  private final RpcNodeJoinMsg _service;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;

  private final Object _clusterStartParam;
}
