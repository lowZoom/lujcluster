package luj.cluster.internal.node.member.actor;

import akka.japi.pf.FI;
import com.google.protobuf.ByteString;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import luj.cluster.internal.node.consul.grpc.gen.NodeCommGrpc;
import luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg;
import luj.cluster.internal.node.member.message.MemberSendRpcMsg;
import luj.cluster.internal.node.message.serialize.invoke.MessageSerializeInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnMemberSendRpc implements FI.UnitApply<MemberSendRpcMsg> {

  OnMemberSendRpc(NodeMemberAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(MemberSendRpcMsg i) throws Exception {
    String targetHost = i.getTargetHost();
    int targetPort = i.getTargetPort();
    String channelKey = targetHost + ":" + targetPort;

    Map<String, ManagedChannel> channelMap = _aktor.getRpcChannelMap();
    ManagedChannel channel = channelMap.computeIfAbsent(channelKey, k -> ManagedChannelBuilder
        .forAddress(targetHost, targetPort)
        .usePlaintext()
        .build());

    LOG.debug("------------发送cluster {} to {}", i.getMessageKey(), channel);

    byte[] data = MessageSerializeInvoker.GET.invoke(i.getMessage(),
        _aktor.getMsgTypeResolver(), _aktor.getMsgCodecMap(), _aktor.getApplicationBean());

    NodeCommGrpc.NodeCommBlockingStub stub = NodeCommGrpc.newBlockingStub(channel)
        .withDeadlineAfter(1, TimeUnit.SECONDS);

    try {
      stub.receive(RpcSendRemoteMsg.newBuilder()
          .setId(i.getMessageKey())
          .setData(ByteString.copyFrom(data))
          .setSenderHost(_aktor.getSelfHost())
          .setSenderPort(_aktor.getSelfPort())
          .build());

    } catch (StatusRuntimeException e) {
      LOG.warn(e.getMessage(), e);

      channelMap.remove(channelKey)
          .shutdown()
          .awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnMemberSendRpc.class);

  private final NodeMemberAktor _aktor;
}
