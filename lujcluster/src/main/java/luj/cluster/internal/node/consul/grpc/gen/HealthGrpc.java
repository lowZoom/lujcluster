package luj.cluster.internal.node.consul.grpc.gen;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.2)",
    comments = "Source: Health.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HealthGrpc {

  private HealthGrpc() {}

  public static final String SERVICE_NAME = "grpc.health.v1.Health";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
      luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getCheckMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Check",
      requestType = luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest.class,
      responseType = luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
      luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getCheckMethod() {
    io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest, luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getCheckMethod;
    if ((getCheckMethod = HealthGrpc.getCheckMethod) == null) {
      synchronized (HealthGrpc.class) {
        if ((getCheckMethod = HealthGrpc.getCheckMethod) == null) {
          HealthGrpc.getCheckMethod = getCheckMethod =
              io.grpc.MethodDescriptor.<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest, luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HealthMethodDescriptorSupplier("Check"))
              .build();
        }
      }
    }
    return getCheckMethod;
  }

  private static volatile io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
      luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getWatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Watch",
      requestType = luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest.class,
      responseType = luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
      luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getWatchMethod() {
    io.grpc.MethodDescriptor<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest, luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> getWatchMethod;
    if ((getWatchMethod = HealthGrpc.getWatchMethod) == null) {
      synchronized (HealthGrpc.class) {
        if ((getWatchMethod = HealthGrpc.getWatchMethod) == null) {
          HealthGrpc.getWatchMethod = getWatchMethod =
              io.grpc.MethodDescriptor.<luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest, luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Watch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HealthMethodDescriptorSupplier("Watch"))
              .build();
        }
      }
    }
    return getWatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HealthStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthStub>() {
        @java.lang.Override
        public HealthStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthStub(channel, callOptions);
        }
      };
    return HealthStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HealthBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthBlockingStub>() {
        @java.lang.Override
        public HealthBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthBlockingStub(channel, callOptions);
        }
      };
    return HealthBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HealthFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HealthFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HealthFutureStub>() {
        @java.lang.Override
        public HealthFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HealthFutureStub(channel, callOptions);
        }
      };
    return HealthFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class HealthImplBase implements io.grpc.BindableService {

    /**
     */
    public void check(luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }

    /**
     */
    public void watch(luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWatchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCheckMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
                luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>(
                  this, METHODID_CHECK)))
          .addMethod(
            getWatchMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest,
                luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>(
                  this, METHODID_WATCH)))
          .build();
    }
  }

  /**
   */
  public static final class HealthStub extends io.grpc.stub.AbstractAsyncStub<HealthStub> {
    private HealthStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthStub(channel, callOptions);
    }

    /**
     */
    public void check(luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void watch(luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request,
        io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getWatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HealthBlockingStub extends io.grpc.stub.AbstractBlockingStub<HealthBlockingStub> {
    private HealthBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthBlockingStub(channel, callOptions);
    }

    /**
     */
    public luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse check(luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> watch(
        luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getWatchMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HealthFutureStub extends io.grpc.stub.AbstractFutureStub<HealthFutureStub> {
    private HealthFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HealthFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HealthFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse> check(
        luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK = 0;
  private static final int METHODID_WATCH = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HealthImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HealthImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK:
          serviceImpl.check((luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>) responseObserver);
          break;
        case METHODID_WATCH:
          serviceImpl.watch((luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest) request,
              (io.grpc.stub.StreamObserver<luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse>) responseObserver);
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

  private static abstract class HealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HealthBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return luj.cluster.internal.node.consul.grpc.gen.HealthOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Health");
    }
  }

  private static final class HealthFileDescriptorSupplier
      extends HealthBaseDescriptorSupplier {
    HealthFileDescriptorSupplier() {}
  }

  private static final class HealthMethodDescriptorSupplier
      extends HealthBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HealthMethodDescriptorSupplier(String methodName) {
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
      synchronized (HealthGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HealthFileDescriptorSupplier())
              .addMethod(getCheckMethod())
              .addMethod(getWatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
