package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.ConfigFactory;
import luj.cluster.internal.node.start.actor.NodeStartAktor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  ClusterNodeStarterImpl(ApplicationContext appContext, String selfAddr, String seedAddr,
      Object startParam) {
    _appContext = appContext;
    _selfAddr = selfAddr;
    _seedAddr = seedAddr;
    _startParam = startParam;
  }

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.empty()
        .withFallback(ConfigFactory.parseString(makeConfigStr()))
        .withFallback(ConfigFactory.parseResources("akka.conf")));

    ClusterBeanCollector.Result beanCollect =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    sys.actorOf(NodeStartAktor.props(beanCollect, _startParam), "start");
  }

  private String makeConfigStr() {
    return String.join("\n", ImmutableList.<String>builder()
        .add("akka.remote.netty.tcp.hostname=\"" + _selfAddr + "\"")
        .add("akka.cluster.seed-nodes=[\"akka.tcp://lujcluster@" + _seedAddr + "\"]")
        .build());
  }

  private final ApplicationContext _appContext;

  private final String _selfAddr;
  private final String _seedAddr;

  private final Object _startParam;
}
