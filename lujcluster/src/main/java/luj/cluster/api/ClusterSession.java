package luj.cluster.api;

import java.util.List;
import java.util.function.Function;
import luj.cluster.api.node.ClusterNode;

public interface ClusterSession {

  interface Start {

    Start selfHost(String val);

    Start selfPort(int val);

    Start selfPortAkka(int val);

    Start selfName(String val);

    Start selfTags(List<String> val);

    Start discoveryConsulHost(String val);

    Start discoveryConsulPort(int val);

    Start discoveryAkkaSeed(List<String> val);

    Start startParam(Object val);
  }

  ClusterNode startNode(Function<Start, Start> startConfig);
}
