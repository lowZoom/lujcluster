package luj.cluster.internal.node.consul.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: NodeComm.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NodeCommGrpc {

  private NodeCommGrpc() {}

  public static final String SERVICE_NAME = "lujcluster.NodeComm";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg,
      com.google.protobuf.Empty> getFireJoinMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "FireJoin",
      requestType = luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg,
      com.google.protobuf.Empty> getFireJoinMethod() {
    io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg, com.google.protobuf.Empty> getFireJoinMethod;
    if ((getFireJoinMethod = NodeCommGrpc.getFireJoinMethod) == null) {
      synchronized (NodeCommGrpc.class) {
        if ((getFireJoinMethod = NodeCommGrpc.getFireJoinMethod) == null) {
          NodeCommGrpc.getFireJoinMethod = getFireJoinMethod =
              io.grpc.MethodDescriptor.<luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "FireJoin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommMethodDescriptorSupplier("FireJoin"))
              .build();
        }
      }
    }
    return getFireJoinMethod;
  }

  private static volatile io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg,
      com.google.protobuf.Empty> getReceiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Receive",
      requestType = luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg,
      com.google.protobuf.Empty> getReceiveMethod() {
    io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg, com.google.protobuf.Empty> getReceiveMethod;
    if ((getReceiveMethod = NodeCommGrpc.getReceiveMethod) == null) {
      synchronized (NodeCommGrpc.class) {
        if ((getReceiveMethod = NodeCommGrpc.getReceiveMethod) == null) {
          NodeCommGrpc.getReceiveMethod = getReceiveMethod =
              io.grpc.MethodDescriptor.<luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Receive"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new NodeCommMethodDescriptorSupplier("Receive"))
              .build();
        }
      }
    }
    return getReceiveMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NodeCommStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommStub>() {
        @java.lang.Override
        public NodeCommStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommStub(channel, callOptions);
        }
      };
    return NodeCommStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NodeCommBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommBlockingStub>() {
        @java.lang.Override
        public NodeCommBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommBlockingStub(channel, callOptions);
        }
      };
    return NodeCommBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NodeCommFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NodeCommFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NodeCommFutureStub>() {
        @java.lang.Override
        public NodeCommFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NodeCommFutureStub(channel, callOptions);
        }
      };
    return NodeCommFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class NodeCommImplBase implements io.grpc.BindableService {

    /**
     */
    public void fireJoin(luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getFireJoinMethod(), responseObserver);
    }

    /**
     */
    public void receive(luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReceiveMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getFireJoinMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg,
                com.google.protobuf.Empty>(
                  this, METHODID_FIRE_JOIN)))
          .addMethod(
            getReceiveMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg,
                com.google.protobuf.Empty>(
                  this, METHODID_RECEIVE)))
          .build();
    }
  }

  /**
   */
  public static final class NodeCommStub extends io.grpc.stub.AbstractAsyncStub<NodeCommStub> {
    private NodeCommStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommStub(channel, callOptions);
    }

    /**
     */
    public void fireJoin(luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getFireJoinMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receive(luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReceiveMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NodeCommBlockingStub extends io.grpc.stub.AbstractBlockingStub<NodeCommBlockingStub> {
    private NodeCommBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty fireJoin(luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getFireJoinMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty receive(luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReceiveMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NodeCommFutureStub extends io.grpc.stub.AbstractFutureStub<NodeCommFutureStub> {
    private NodeCommFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NodeCommFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NodeCommFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> fireJoin(
        luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getFireJoinMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> receive(
        luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReceiveMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_FIRE_JOIN = 0;
  private static final int METHODID_RECEIVE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NodeCommImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NodeCommImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_FIRE_JOIN:
          serviceImpl.fireJoin((luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_RECEIVE:
          serviceImpl.receive((luj.cluster.internal.node.consul.grpc.gen.RpcSendRemoteMsg) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NodeCommBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NodeCommBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return luj.cluster.internal.node.consul.grpc.gen.NodeCommOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NodeComm");
    }
  }

  private static final class NodeCommFileDescriptorSupplier
      extends NodeCommBaseDescriptorSupplier {
    NodeCommFileDescriptorSupplier() {}
  }

  private static final class NodeCommMethodDescriptorSupplier
      extends NodeCommBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NodeCommMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NodeCommGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NodeCommFileDescriptorSupplier())
              .addMethod(getFireJoinMethod())
              .addMethod(getReceiveMethod())
              .build();
        }
      }
    }
    return result;
  }
}
