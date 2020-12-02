package luj.cluster.api.node;

public interface NodeMessageSerializer<M> {

  interface Context {

    <T> T getApplicationBean();
  }

  byte[] serialize(Context ctx, M msg);

  M deserialize(Context ctx, byte[] bytes);
}
