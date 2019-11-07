package luj.cluster.api.actor;

import akka.actor.ActorRef;
import luj.cluster.api.logging.Log;

public interface ActorMessageHandler<A, M> {

  interface Context {

    /**
     * @see #getActorState
     */
    @Deprecated
    <A> A getActor(ActorMessageHandler<A, ?> handler);

    <A> A getActorState(ActorMessageHandler<A, ?> handler);

    <M> M getMessage(ActorMessageHandler<?, M> handler);

    Log getLogger();

    Node getRemoteNode();

    ActorRef createActor(Object actorState);
  }

  interface Node {

    String getIp();

    void sendMessage(Object msg);
  }

  void onHandle(Context ctx);
}
