package luj.cluster.api;

import java.util.List;

public interface ClusterSession {

  /**
   * @see #startNode(String, int, List, Object)
   */
  @Deprecated
  void startNode(String host, int port, String seedAddr, Object startParam);

  void startNode(String host, int port, List<String> seedList, Object startParam);
}
