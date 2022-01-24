package luj.cluster.internal.node.consul.grpc;

import akka.actor.ActorRef;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.consul.grpc.gen.NodeCommGrpc;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg;
import luj.cluster.internal.node.member.join.trigger2.JoinConsulOneTrigger;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemote2Msg;


final class NodeGrpcImpl extends NodeCommGrpc.NodeCommImplBase {

  @Override
  public void fireJoin(RpcNodeJoinMsg request, StreamObserver<Empty> responseObserver) {
    new JoinConsulOneTrigger(_joinListener, request,
        _receiveRef, _memberRef, _clusterStartParam).trigger();

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }

  /**
   * 收到远程节点发来的消息
   */
  @Override
  public void receive(RpcSendRemoteMsg request, StreamObserver<Empty> responseObserver) {
//    LOG.debug("-----收到cluster {} from {}:{}",
//        request.getId(), request.getSenderHost(), request.getSenderPort());

    byte[] data = request.getData().toByteArray();
    String senderHost = request.getSenderHost();
    int senderPort = request.getSenderPort();

    _receiveRef.tell(new NodeSendRemote2Msg(
        request.getId(), data, senderHost, senderPort), ActorRef.noSender());

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }

//  private static final Logger LOG = LoggerFactory.getLogger(NodeGrpcImpl.class);

  NodeNewMemberListener _joinListener;
  Object _clusterStartParam;

  ActorRef _receiveRef;
  ActorRef _memberRef;
}
