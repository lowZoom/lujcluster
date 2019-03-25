package luj.cluster.api.actor;

import luj.cluster.api.logging.Log;

public interface ActorMessageHandler<A, M> {

  interface Context {

    <A> A getActor(ActorMessageHandler<A, ?> handler);

    <M> M getMessage(ActorMessageHandler<?, M> handler);

    Log getLogger();
  }

  void onHandle(Context ctx);
}
