package luj.cluster.internal.node.consul.grpc;

import akka.actor.ActorRef;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import java.util.concurrent.TimeUnit;
import luj.cluster.internal.node.consul.grpc.gen.NodeCommGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeGrpcStarter {

  public NodeGrpcStarter(String selfHost, int selfPort, ActorRef receiveRef) {
    _selfHost = selfHost;
    _selfPort = selfPort;
    _receiveRef = receiveRef;
  }

  public void start() throws Exception {
    NodeGrpcImpl nodeComm = new NodeGrpcImpl();
    nodeComm._receiveRef = _receiveRef;

    ServerBuilder.forPort(_selfPort)
        .addService(nodeComm)
        .addService(new HealthImpl())
        .build().start();

    awaitStart();
//    LOG.debug("gggGRPC启动完成：{}", _selfPort);
  }

  private void awaitStart() throws InterruptedException {
    ManagedChannel channel = ManagedChannelBuilder.forAddress(_selfHost, _selfPort)
        .usePlaintext()
        .build();

    while (true) {
      try {
        NodeCommGrpc.newBlockingStub(channel).ping(Empty.getDefaultInstance());
        break;
      } catch (StatusRuntimeException e) {
        LOG.debug("gggGRPC启动中。。。 {}", channel);
        Thread.sleep(500);
      }
    }

    channel.shutdown().awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeGrpcStarter.class);

  private final String _selfHost;
  private final int _selfPort;

  private final ActorRef _receiveRef;
}
