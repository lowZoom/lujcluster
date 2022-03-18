package luj.cluster.internal.node.member.join.trigger2;

import com.ecwid.consul.v1.health.model.HealthService;
import java.util.Collection;
import luj.cluster.internal.node.consul.grpc.gen.RpcNodeJoinMsg;
import luj.cluster.internal.node.member.join.trigger2.remote.JoinRemoteFirer;
import luj.cluster.internal.node.start.ClusterNodeStarter;

public class JoinConsulTrigger {

  public JoinConsulTrigger(Collection<HealthService.Service> serviceList,
      ClusterNodeStarter.Config nodeConfig) {
    _serviceList = serviceList;
    _nodeConfig = nodeConfig;
  }

  public void trigger() {
    String selfHost = _nodeConfig.selfHost();
    int selfPort = _nodeConfig.selfPort();

    JoinRemoteFirer.Channel channel = new JoinRemoteFirer(selfHost, selfPort).makeChannel();
    for (HealthService.Service service : _serviceList) {
//      LOG.debug("我调我自己己己己己己己己己己己己己己己己己己己己 {}", _serviceList.size());
      fireOne(channel, service);
    }

    channel.shutdownAndWait();
  }

  private void fireOne(JoinRemoteFirer.Channel channel, HealthService.Service service) {
    channel.fireJoin(RpcNodeJoinMsg.newBuilder()
        .setHost(service.getAddress())
        .setPort(service.getPort())
        .setName(service.getService())
        .addAllTag(service.getTags())
        .build());
  }

//  private static final Logger LOG = LoggerFactory.getLogger(JoinConsulTrigger.class);

  private final Collection<HealthService.Service> _serviceList;
  private final ClusterNodeStarter.Config _nodeConfig;
}
