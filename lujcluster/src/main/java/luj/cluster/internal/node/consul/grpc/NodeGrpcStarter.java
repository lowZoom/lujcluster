package luj.cluster.internal.node.consul.grpc;

import akka.actor.ActorRef;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.internal.node.start.ClusterNodeStarter;

public class NodeGrpcStarter {

  public NodeGrpcStarter(ClusterNodeStarter.Config nodeConfig, NodeNewMemberListener joinListener,
      ActorRef receiveRef, ActorRef memberRef) {
    _nodeConfig = nodeConfig;
    _joinListener = joinListener;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
  }

  public Server start() throws Exception {
    NodeGrpcImpl nodeComm = new NodeGrpcImpl();
    nodeComm._joinListener = _joinListener;
    nodeComm._clusterStartParam = _nodeConfig.startParam();
    nodeComm._receiveRef = _receiveRef;
    nodeComm._memberRef = _memberRef;

    Server grpc = ServerBuilder.forPort(_nodeConfig.selfPort())
        .addService(nodeComm)
        .addService(new HealthImpl())
        .build();

    grpc.start();
//    LOG.debug("gggGRPC启动完成：{}", _nodeConfig.selfPort());

    return grpc;
  }

//  private static final Logger LOG = LoggerFactory.getLogger(NodeGrpcStarter.class);

  private final ClusterNodeStarter.Config _nodeConfig;
  private final NodeNewMemberListener _joinListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
