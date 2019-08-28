package luj.cluster.api;

public interface ClusterSession {

  void startNode(String host, int port, String seedAddr, Object startParam);
}
