package luj.cluster.api.node.message;

public interface NodeMessageSerializer<M> {

  interface Context {

    <T> T getApplicationBean();
  }

  byte[] serialize(Context ctx, M message);

  Object deserialize(Context ctx, byte[] bytes);
}
