package luj.cluster.internal.node.consul.start;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import luj.cluster.internal.node.consul.actor.MemberConsulAktor;
import luj.cluster.internal.node.consul.actor.message.StartConsulMsg;
import luj.cluster.internal.node.member.actor.NodeMemberAktor;
import luj.cluster.internal.node.start.ClusterNodeStarter;

public class NodeConsulStarter {

  public NodeConsulStarter(NodeMemberAktor aktor, ClusterNodeStarter.Config nodeConfig) {
    _aktor = aktor;
    _nodeConfig = nodeConfig;
  }

  public void start() {
    String consulHost = _nodeConfig.discoveryConsulHost();
    if (consulHost == null) {
//      LOG.debug("[cluster]不使用consul");
      return;
    }

    //TODO: 校验name不一样的地址重复

    ActorContext context = _aktor.context();
    ActorRef consulRef = context.actorOf(MemberConsulAktor.props(_aktor), "consul");
    consulRef.tell(new StartConsulMsg(_nodeConfig), _aktor.self());
  }

//  private static final Logger LOG = LoggerFactory.getLogger(NodeConsulStarter.class);

  private final NodeMemberAktor _aktor;
  private final ClusterNodeStarter.Config _nodeConfig;
}
