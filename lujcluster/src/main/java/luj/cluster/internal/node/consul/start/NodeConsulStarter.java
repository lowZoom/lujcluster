package luj.cluster.internal.node.consul.start;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.catalog.CatalogServicesRequest;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.internal.node.consul.grpc.NodeGrpcStarter;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import luj.cluster.internal.node.consul.health.ConsulHealthWatcher;
import luj.cluster.internal.node.member.join.trigger2.JoinConsulTrigger;
import luj.cluster.internal.node.member.join.trigger2.remote.JoinRemoteFirer;
import luj.cluster.internal.node.start.ClusterNodeStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeConsulStarter {

  public NodeConsulStarter(ClusterNodeStarter.Config nodeConfig, NodeNewMemberListener joinListener,
      NodeMemberHealthListener healthListener, ActorRef receiveRef, ActorRef memberRef) {
    _nodeConfig = nodeConfig;
    _joinListener = joinListener;
    _healthListener = healthListener;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
  }

  public void start() throws Exception {
    String consulHost = _nodeConfig.discoveryConsulHost();
    if (consulHost == null) {
//      LOG.debug("[cluster]不使用consul");
      return;
    }

    // 在注册前先启动好rpc
    new NodeGrpcStarter(_nodeConfig, _joinListener, _receiveRef, _memberRef).start();

//    LOG.debug("[cluster]注册到consul：{}", _nodeConfig.selfName());
    ConsulClient consul = new ConsulClient(consulHost, _nodeConfig.discoveryConsulPort());
    registerSelf(consul);

    handleOtherJoin(consul);
    watchHealth(consul);
  }

  private void watchHealth(ConsulClient consul) {
    String selfName = _nodeConfig.selfName();
    new ConsulHealthWatcher(consul, selfName, _healthListener, _receiveRef, _memberRef).watch();
  }

  private void registerSelf(ConsulClient consul) {
    String selfHost = _nodeConfig.selfHost();
    int selfPort = _nodeConfig.selfPort();

    NewService service = new NewService();
    service.setAddress(selfHost);
    service.setPort(selfPort);

    String selfName = _nodeConfig.selfName();
    service.setName(selfName);
    service.setTags(_nodeConfig.selfTags());

    NewService.Check check = new NewService.Check();
    check.setGrpc(selfHost + ":" + selfPort);
    check.setInterval("6s");
    service.setCheck(check);

//    consul.agentServiceDeregister(selfName);
    consul.agentServiceRegister(service);
  }

  private void handleOtherJoin(ConsulClient consul) {
    CatalogServicesRequest req = CatalogServicesRequest.newBuilder().build();
    String selfName = _nodeConfig.selfName();

    Collection<HealthService.Service> services = consul.getCatalogServices(req)
        .getValue().keySet().parallelStream()
        .filter(s -> !selfName.equals(s))
        .flatMap(s -> getHealthServices(consul, s).stream())
        .map(HealthService::getService)
        .filter(s -> !s.getAddress().isEmpty())
        .collect(Collectors.toList());

//    LOG.debug("services：{}", services);
    new JoinConsulTrigger(services, _nodeConfig).trigger();

    for (HealthService.Service s : services) {
      notifySelfJoin(s);
    }
  }

  private List<HealthService> getHealthServices(ConsulClient consul, String service) {
    return consul.getHealthServices(service, HealthServicesRequest.newBuilder()
//        .setPassing(true)
        .build()).getValue();
  }

  private void notifySelfJoin(HealthService.Service service) {
    String host = service.getAddress();
    int port = service.getPort();

    JoinRemoteFirer.Channel channel = new JoinRemoteFirer(host, port).makeChannel();
//    LOG.debug("通知远端自己自己自己自己 {}", channel);

    channel.fireJoin(RpcNodeJoinMsg.newBuilder()
        .setHost(_nodeConfig.selfHost())
        .setPort(_nodeConfig.selfPort())
        .setName(_nodeConfig.selfName())
        .addAllTag(_nodeConfig.selfTags())
        .build());

    channel.shutdownAndWait();
  }

  private static final Logger LOG = LoggerFactory.getLogger(NodeConsulStarter.class);

  private final ClusterNodeStarter.Config _nodeConfig;

  private final NodeNewMemberListener _joinListener;
  private final NodeMemberHealthListener _healthListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
