package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import luj.cluster.internal.node.start.actor.NodeStartActor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  ClusterNodeStarterImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("LujCluster",
        ConfigFactory.parseString("akka{loglevel=DEBUG\nstdout-loglevel=DEBUG}"));

    ClusterBeanCollector.Result collectResult =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    sys.actorOf(NodeStartActor.props(collectResult));
  }

  private final ApplicationContext _appContext;
}
