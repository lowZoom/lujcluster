package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import luj.cluster.internal.node.start.actor.NodeStartAktor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  ClusterNodeStarterImpl(ApplicationContext appContext, String seedAddr,
      Object startParam) {
    _appContext = appContext;
    _seedAddr = seedAddr;
    _startParam = startParam;
  }

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.empty()
        .withFallback(ConfigFactory.parseString("akka.cluster.seed-nodes=[\"akka.tcp://lujcluster@"+_seedAddr+"\"]"))
        .withFallback(ConfigFactory.parseResources("akka.conf")));

    ClusterBeanCollector.Result beanCollect =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    sys.actorOf(NodeStartAktor.props(beanCollect, _startParam), "start");
  }

  private final ApplicationContext _appContext;
  private final String _seedAddr;

  private final Object _startParam;
}
