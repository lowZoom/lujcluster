package luj.cluster.api.node;

public interface NodeShutdownListener {

  interface Context {

    <T> T getStartParam();
  }

  void onShutdown(Context ctx) throws Exception;
}
