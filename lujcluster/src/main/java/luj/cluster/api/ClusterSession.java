package luj.cluster.api;

public interface ClusterSession {

  void startNode(String seedAddr, Object startParam);
}
