package luj.cluster.internal.node.member.join.trigger2.remote;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import java.util.concurrent.TimeUnit;
import luj.cluster.internal.node.consul.grpc.gen.NodeCommGrpc;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinRemoteFirer {

  public interface Channel {

    void fireJoin(RpcNodeJoinMsg msg);

    void shutdownAndWait();
  }

  public JoinRemoteFirer(String remoteHost, int remotePort) {
    _remoteHost = remoteHost;
    _remotePort = remotePort;
  }

  public Channel makeChannel() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress(_remoteHost, _remotePort)
        .usePlaintext()
        .build();

    return new Channel() {
      @Override
      public void fireJoin(RpcNodeJoinMsg msg) {
        fireImpl(channel, msg);
      }

      @Override
      public void shutdownAndWait() {
        shutdownImpl(channel);
      }

      @Override
      public String toString() {
        return channel.toString();
      }
    };
  }

  private void fireImpl(ManagedChannel channel, RpcNodeJoinMsg msg) {
    try {
      NodeCommGrpc.newBlockingStub(channel)
//          .withDeadlineAfter(1, TimeUnit.SECONDS)
          .fireJoin(msg);

    } catch (StatusRuntimeException e) {
      LOG.warn(e.getMessage(), e);
    }
  }

  private void shutdownImpl(ManagedChannel channel) {
    try {
      channel.shutdown().awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(JoinRemoteFirer.class);

  private final String _remoteHost;
  private final int _remotePort;
}
