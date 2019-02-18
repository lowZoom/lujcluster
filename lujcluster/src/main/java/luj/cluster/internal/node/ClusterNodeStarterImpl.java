package luj.cluster.internal.node;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.ConfigFactory;

final class ClusterNodeStarterImpl implements ClusterNodeStarter {

  @Override
  public void start() {
    ActorSystem sys = ActorSystem.create("LujCluster", ConfigFactory.parseString("akka{}"));

    sys.actorOf(Props.create(NodeStartActor.class, NodeStartActor::new));
  }
}
