package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.ConfigFactory;
import java.util.List;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.message.serialize.AkkaSerializeInitializer;
import luj.cluster.internal.node.start.actor.NodeStartAktor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

public class ClusterNodeStarter {

  public ClusterNodeStarter(ApplicationContext appContext, String selfHost, int selfPort,
      List<String> seedList, Object startParam) {
    _appContext = appContext;
    _selfHost = selfHost;
    _selfPort = selfPort;
    _seedList = seedList;
    _startParam = startParam;
  }

  public ClusterNode start() {
    ClusterBeanCollector.Result beanCollect =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    new AkkaSerializeInitializer(beanCollect, _startParam).init();
    boolean clusterEnabled = _selfHost != null;

    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.empty()
        .withFallback(ConfigFactory.parseString(makeConfigStr(!clusterEnabled)))
        .withFallback(ConfigFactory.parseResources("akka.conf")));

    ClusterNodeImpl node = new ClusterNodeImpl();
    sys.actorOf(NodeStartAktor.props(beanCollect,
        _startParam, node::setReceiveRef, clusterEnabled), "start");

    return node;
  }

  private String makeConfigStr(boolean clusterDisabled) {
    List<String> clusterConf = clusterDisabled ? ImmutableList.of()
        : new AkkaClusterConfigMaker(_selfHost, _selfPort, _seedList).make();

    return String.join("\n", ImmutableList.<String>builder()
        .addAll(clusterConf)
        .build());
  }

  private final ApplicationContext _appContext;

  private final String _selfHost;
  private final int _selfPort;

  private final List<String> _seedList;
  private final Object _startParam;
}
