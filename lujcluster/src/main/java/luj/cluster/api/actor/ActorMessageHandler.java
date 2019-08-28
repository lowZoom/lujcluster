package luj.cluster.api.actor;

import luj.cluster.api.logging.Log;

public interface ActorMessageHandler<A, M> {

  interface Context {

    <A> A getActor(ActorMessageHandler<A, ?> handler);

    <M> M getMessage(ActorMessageHandler<?, M> handler);

    Log getLogger();

    Node getRemoteNode();
  }

  interface Node {

    String getIp();
  }

  void onHandle(Context ctx);
}
