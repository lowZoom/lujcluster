package luj.cluster.internal.node.start;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.stream.Collectors;
import luj.cluster.internal.node.message.receive.message.remote.NodeSendRemoteMsg;
import luj.cluster.internal.node.message.serialize.AkkaMessageSerializer;

final class AkkaClusterConfigMaker {

  AkkaClusterConfigMaker(String selfHost, int selfPort, List<String> seedList) {
    _selfHost = selfHost;
    _selfPort = selfPort;
    _seedList = seedList;
  }

  public List<String> make() {
    return ImmutableList.<String>builder()
        .add(strField("akka.actor.provider", "cluster"))

        .add(strField("akka.remote.netty.tcp.hostname", _selfHost))
        .add("akka.remote.netty.tcp.port=" + _selfPort)

        // https://doc.akka.io/docs/akka/2.5/remoting.html?language=java#akka-behind-nat-or-in-a-docker-container
        .add(strField("akka.remote.netty.tcp.bind-hostname", "0.0.0.0"))
        .add("akka.remote.netty.tcp.bind-port=" + _selfPort)

        .add("akka.cluster.seed-nodes=[" + makeSeedStr() + "]")

        .add(strField("akka.actor.serializers.luj", AkkaMessageSerializer.class.getName()))
        .add("akka.actor.serialization-bindings {\n"
            + strField("\"" + NodeSendRemoteMsg.class.getName() + "\"", "luj")
            + "\n}")

        .build();
  }

  private String makeSeedStr() {
    return _seedList.stream()
        .map(s -> "\"akka.tcp://lujcluster@" + s + "\"")
        .collect(Collectors.joining(",\n"));
  }

  private String strField(String key, String value) {
    return key + "=\"" + value + "\"";
  }

  private final String _selfHost;
  private final int _selfPort;

  private final List<String> _seedList;
}
