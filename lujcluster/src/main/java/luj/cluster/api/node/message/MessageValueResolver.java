package luj.cluster.api.node.message;

public interface MessageValueResolver {

  Object resolve(Object msg);
}
