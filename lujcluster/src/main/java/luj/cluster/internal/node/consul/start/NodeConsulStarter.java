package luj.cluster.internal.node.consul.start;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.agent.model.Service;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.internal.node.consul.grpc.NodeGrpcStarter;
import luj.cluster.internal.node.member.join.trigger2.JoinConsulTrigger;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeConsulStarter {

  public NodeConsulStarter(ClusterNodeStarter.Config nodeConfig, NodeNewMemberListener joinListener,
      ActorRef receiveRef, ActorRef memberRef, Object clusterStartParam) {
    _nodeConfig = nodeConfig;
    _joinListener = joinListener;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
    _clusterStartParam = clusterStartParam;
  }

  public void start() throws Exception {
    String consulHost = _nodeConfig.discoveryConsulHost();
    if (consulHost == null) {
      LOG.debug("[cluster]不使用consul");
      return;
    }

    String selfHost = _nodeConfig.selfHost();
    int selfPort = _nodeConfig.selfPort();
    new NodeGrpcStarter(selfHost, selfPort, _receiveRef).start();

    String selfName = _nodeConfig.selfName();
    LOG.debug("[cluster]注册到consul：{}", selfName);

    NewService service = new NewService();
    service.setAddress(selfHost);
    service.setPort(selfPort);

    service.setName(selfName);
    service.setTags(_nodeConfig.selfTags());

    NewService.Check check = new NewService.Check();
    check.setGrpc(selfHost + ":" + selfPort);
    check.setInterval("6s");
    service.setCheck(check);

    ConsulClient consul = new ConsulClient(consulHost, _nodeConfig.discoveryConsulPort());
//    consul.agentServiceDeregister(name);
    consul.agentServiceRegister(service);

    Collection<HealthService.Service> services = consul.getAgentServices().getValue()
        .values().parallelStream()
        .flatMap(s -> getHealthServices(consul, s).stream())
        .map(HealthService::getService)
        .collect(Collectors.toList());

    LOG.debug("services：{}", services);

    new JoinConsulTrigger(_joinListener, services,
        _receiveRef, _memberRef, _clusterStartParam).trigger();
  }

  private List<HealthService> getHealthServices(ConsulClient consul, Service s) {
    HealthServicesRequest passing = HealthServicesRequest.newBuilder()
        .setPassing(true)
        .build();
    return consul.getHealthServices(s.getService(), passing).getValue();
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeConsulStarter.class);

  private final ClusterNodeStarter.Config _nodeConfig;
  private final NodeNewMemberListener _joinListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;

  private final Object _clusterStartParam;
}
