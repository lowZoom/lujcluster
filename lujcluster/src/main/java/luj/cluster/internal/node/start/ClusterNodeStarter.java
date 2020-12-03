package luj.cluster.internal.node.start;

import akka.actor.ActorSystem;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.ConfigFactory;
import java.util.List;
import java.util.stream.Collectors;
import luj.cluster.api.node.ClusterNode;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.serialize.AkkaMessageSerializer;
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

    ActorSystem sys = ActorSystem.create("lujcluster", ConfigFactory.empty()
        .withFallback(ConfigFactory.parseString(makeConfigStr()))
        .withFallback(ConfigFactory.parseResources("akka.conf")));

    ClusterNodeImpl node = new ClusterNodeImpl();
    sys.actorOf(NodeStartAktor.props(beanCollect, _startParam, node::setReceiveRef), "start");

    return node;
  }

  private String makeConfigStr() {
    return String.join("\n", ImmutableList.<String>builder()
        .add(strField("akka.remote.netty.tcp.hostname", _selfHost))
        .add("akka.remote.netty.tcp.port=" + _selfPort)

        // https://doc.akka.io/docs/akka/2.5/remoting.html?language=scala#akka-behind-nat-or-in-a-docker-container
        .add(strField("akka.remote.netty.tcp.bind-hostname", "0.0.0.0"))
        .add("akka.remote.netty.tcp.bind-port=" + _selfPort)

        .add("akka.cluster.seed-nodes=[" + makeSeedStr() + "]")

        .add(strField("akka.actor.serializers.luj", AkkaMessageSerializer.class.getName()))
        .add("akka.actor.serialization-bindings {\n"
            + strField("\"" + NodeSendRemoteMsg.class.getName() + "\"", "luj")
            + "\n}")

        .build());
  }

  private String makeSeedStr() {
    return _seedList.stream()
        .map(s -> "\"akka.tcp://lujcluster@" + s + "\"")
        .collect(Collectors.joining(",\n"));
  }

  private String strField(String key, String value) {
    return key + "=\"" + value + "\"";
  }

  private final ApplicationContext _appContext;

  private final String _selfHost;
  private final int _selfPort;

  private final List<String> _seedList;
  private final Object _startParam;
}
