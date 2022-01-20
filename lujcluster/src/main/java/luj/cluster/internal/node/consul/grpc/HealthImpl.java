package luj.cluster.internal.node.consul.grpc;

import io.grpc.stub.StreamObserver;
import luj.cluster.internal.node.consul.grpc.gen.HealthCheckRequest;
import luj.cluster.internal.node.consul.grpc.gen.HealthCheckResponse;
import luj.cluster.internal.node.consul.grpc.gen.HealthGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class HealthImpl extends HealthGrpc.HealthImplBase {

  @Override
  public void check(HealthCheckRequest request,
      StreamObserver<HealthCheckResponse> responseObserver) {
//    LOG.debug("checkcheckcheckcheckcheckcheckcheckcheckcheckcheck");

    responseObserver.onNext(HealthCheckResponse.newBuilder()
        .setStatus(HealthCheckResponse.ServingStatus.SERVING)
        .build());

    responseObserver.onCompleted();
  }

  @Override
  public void watch(HealthCheckRequest request,
      StreamObserver<HealthCheckResponse> responseObserver) {
    LOG.debug("watchwatchwatchwatchwatchwatchwatchwatchwatchwatch");

    responseObserver.onNext(HealthCheckResponse.newBuilder()
        .setStatus(HealthCheckResponse.ServingStatus.SERVING)
        .build());

    responseObserver.onCompleted();
  }

  private static final Logger LOG = LoggerFactory.getLogger(HealthImpl.class);
}
