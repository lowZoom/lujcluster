package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import luj.cluster.internal.node.start.actor.NodeStartActor;
import luj.cluster.internal.session.inject.ClusterBeanCollector;
import org.springframework.context.ApplicationContext;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  ClusterNodeStarterImpl(ApplicationContext appContext, Object startParam) {
    _appContext = appContext;
    _startParam = startParam;
  }

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.parseResources("akka.conf"));

    ClusterBeanCollector.Result beanCollect =
        ClusterBeanCollector.Factory.create(_appContext).collect();

    sys.actorOf(NodeStartActor.props(beanCollect, _startParam), "start");
  }

  private final ApplicationContext _appContext;

  private final Object _startParam;
}
