package luj.cluster.api;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import luj.cluster.api.node.ClusterNode;

public interface ClusterSession {

  interface Start {

    Start selfHost(String val);

    Start selfPort(int val);

    Start selfPortAkka(int val);

    Start selfName(String val);

    Start selfTags(List<String> val);

    Start discoveryAkkaSeed(List<String> val);

    Start discoveryConsulHost(String val);

    Start discoveryConsulPort(int val);

    Start startParam(Object val);
  }

  /**
   * @see #startNode(Function)
   */
  @Deprecated
  void startNode(String host, int port, String seedAddr, Object startParam);

  /**
   * @see #startNode(Function)
   */
  @Deprecated
  ClusterNode startNode(String host, int port, List<String> seedList, Object startParam);

  ClusterNode startNode(Function<Start, Start> startConfig);
}
