package luj.cluster.internal.node.consul.grpc;

import akka.actor.ActorRef;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import luj.cluster.internal.node.consul.grpc.gen.NodeCommGrpc;
import luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemote2Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 收到远程节点发来的消息
 */
final class NodeGrpcImpl extends NodeCommGrpc.NodeCommImplBase {

  @Override
  public void ping(Empty request, StreamObserver<Empty> responseObserver) {
    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }

  @Override
  public void receive(RpcSendRemoteMsg request, StreamObserver<Empty> responseObserver) {
    LOG.debug("-----收到cluster {} from {}:{}",
        request.getId(), request.getSenderHost(), request.getSenderPort());

    byte[] data = request.getData().toByteArray();
    String senderHost = request.getSenderHost();
    int senderPort = request.getSenderPort();

    _receiveRef.tell(new NodeSendRemote2Msg(
        request.getId(), data, senderHost, senderPort), ActorRef.noSender());

    responseObserver.onNext(Empty.getDefaultInstance());
    responseObserver.onCompleted();
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeGrpcImpl.class);

  ActorRef _receiveRef;
}
