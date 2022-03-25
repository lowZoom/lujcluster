package luj.cluster.internal.node.consul.actor;

import akka.japi.pf.FI;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;
import com.ecwid.consul.v1.catalog.CatalogServicesRequest;
import com.ecwid.consul.v1.health.HealthServicesRequest;
import com.ecwid.consul.v1.health.model.HealthService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.Server;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import luj.cluster.internal.node.consul.actor.message.StartConsulMsg;
import luj.cluster.internal.node.consul.grpc.NodeGrpcStarter;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import luj.cluster.internal.node.consul.health.ConsulHealthWatcher;
import luj.cluster.internal.node.member.join.trigger2.JoinConsulTrigger;
import luj.cluster.internal.node.member.join.trigger2.remote.JoinRemoteFirer;
import luj.cluster.internal.node.start.ClusterNodeStarter;

final class OnStartConsul implements FI.UnitApply<StartConsulMsg> {

  OnStartConsul(MemberConsulAktor aktor) {
    _aktor = aktor;
  }

  @Override
  public void apply(StartConsulMsg i) throws Exception {
    //TODO: 校验name不一样的地址重复

    ClusterNodeStarter.Config nodeConfig = i.getNodeConfig();
    _aktor.setSelfName(nodeConfig.selfName());

    // 在注册前先启动好rpc
    Server grpc = new NodeGrpcStarter(nodeConfig,
        _aktor.getJoinListener(), _aktor.getReceiveRef(), _aktor.getMemberRef()).start();

//    LOG.debug("[cluster]注册到consul：{}", _nodeConfig.selfName());
    ConsulClient consul = new ConsulClient(
        nodeConfig.discoveryConsulHost(), nodeConfig.discoveryConsulPort());

    _aktor.setGrpc(grpc);
    _aktor.setConsul(consul);

    registerSelf(consul, nodeConfig);
    handleOtherJoin(consul, nodeConfig);
    watchHealth(consul, nodeConfig);
  }

  private void watchHealth(ConsulClient consul, ClusterNodeStarter.Config config) {
    ExecutorService exec = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
        .setNameFormat("consul-watch-%d")
        .build());

    _aktor.setWatchExec(exec);
    String selfName = config.selfName();

    new ConsulHealthWatcher(consul, exec, selfName,
        _aktor.getHealthListener(), _aktor.getReceiveRef(), _aktor.getMemberRef()).watch();
  }

  private void registerSelf(ConsulClient consul, ClusterNodeStarter.Config config) {
    String selfHost = config.selfHost();
    int selfPort = config.selfPort();

    NewService.Check check = new NewService.Check();
    check.setGrpc(selfHost + ":" + selfPort);
    check.setInterval("6s");

    NewService service = new NewService();
    service.setAddress(selfHost);
    service.setPort(selfPort);

    service.setName(config.selfName());
    service.setTags(config.selfTags());
    service.setCheck(check);

    consul.agentServiceRegister(service);
  }

  private void handleOtherJoin(ConsulClient consul, ClusterNodeStarter.Config config) {
    CatalogServicesRequest req = CatalogServicesRequest.newBuilder().build();
    String selfName = config.selfName();

    Collection<HealthService.Service> services = consul.getCatalogServices(req)
        .getValue().keySet().parallelStream()
        .filter(s -> !selfName.equals(s))
        .flatMap(s -> getHealthServices(consul, s).stream())
        .map(HealthService::getService)
        .filter(s -> !s.getAddress().isEmpty())
        .collect(Collectors.toList());

//    LOG.debug("services：{}", services);
    new JoinConsulTrigger(services, config).trigger();

    for (HealthService.Service s : services) {
      notifySelfJoin(s, config);
    }
  }

  private List<HealthService> getHealthServices(ConsulClient consul, String service) {
    return consul.getHealthServices(service, HealthServicesRequest.newBuilder()
//        .setPassing(true)
        .build()).getValue();
  }

  private void notifySelfJoin(HealthService.Service service, ClusterNodeStarter.Config config) {
    String host = service.getAddress();
    int port = service.getPort();

    JoinRemoteFirer.Channel channel = new JoinRemoteFirer(host, port).makeChannel();
//    LOG.debug("通知远端自己自己自己自己 {}", channel);

    channel.fireJoin(RpcNodeJoinMsg.newBuilder()
        .setHost(config.selfHost())
        .setPort(config.selfPort())
        .setName(config.selfName())
        .addAllTag(config.selfTags())
        .build());

    channel.shutdownAndWait();
  }

  private final MemberConsulAktor _aktor;
}
