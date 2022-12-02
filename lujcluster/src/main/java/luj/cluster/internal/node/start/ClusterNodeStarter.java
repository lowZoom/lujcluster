package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.ConfigFactory;
import java.util.List;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.start.actor.NodeStartAktor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

public class ClusterNodeStarter {

  public interface Config {

    String selfHost();

    int selfPort();

    int selfPortAkka();

    String selfName();

    List<String> selfTags();

    List<String> discoveryAkkaSeed();

    String discoveryConsulHost();

    int discoveryConsulPort();

    Object startParam();
  }

  public ClusterNodeStarter(ApplicationContext appContext, Config config) {
    _appContext = appContext;
    _config = config;
  }

  public ClusterNode start() {
    ClusterBeanCollector.Result beanCollect =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    boolean clusterEnabled = !_config.discoveryAkkaSeed().isEmpty();
    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.empty()
        .withFallback(ConfigFactory.parseString(makeConfigStr(!clusterEnabled)))
        .withFallback(ConfigFactory.parseResources("akka.conf")));

    var node = new ClusterNodeImpl();
    node.setActorSystem(sys);

    sys.actorOf(NodeStartAktor.props(beanCollect,
        _config, clusterEnabled, node::setReceiveRef), "start");

    return node;
  }

  private String makeConfigStr(boolean clusterDisabled) {
    String selfHost = _config.selfHost();
    List<String> akkaSeed = _config.discoveryAkkaSeed();

    int portOverride = _config.selfPortAkka();
    int selfPort = portOverride > 0 ? portOverride : _config.selfPort();

    List<String> clusterConf = clusterDisabled ? ImmutableList.of() :
        new AkkaClusterConfigMaker(selfHost, selfPort, akkaSeed).make();

    return String.join("\n", ImmutableList.<String>builder()
        .addAll(clusterConf)
        .build());
  }

  private final ApplicationContext _appContext;

  private final Config _config;
}
