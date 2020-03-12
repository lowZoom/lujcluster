package luj.cluster.api.actor;

import akka.actor.ActorRef;
import java.time.Duration;
import luj.cluster.api.logging.Log;

public interface ActorMessageHandler<A, M> {

  interface Context {

    /**
     * @see #getActorState
     */
    @Deprecated
    <A> A getActor(ActorMessageHandler<A, ?> handler);

    <A> A getActorState(ActorMessageHandler<A, ?> handler);

    Ref getActorRef();

    <M> M getMessage(ActorMessageHandler<?, M> handler);

    Log getLogger();

    Node getRemoteNode();

    ActorRef createActor(Object actorState);
  }

  interface Node {

    String getIp();

    /**
     * @see #sendMessage(String, Object)
     */
    @Deprecated
    void sendMessage(Object msg);

    void sendMessage(String msgKey, Object msg);
  }

  interface Ref {

    void tell(Object msg, Duration delay);
  }

  void onHandle(Context ctx) throws Exception;
}
