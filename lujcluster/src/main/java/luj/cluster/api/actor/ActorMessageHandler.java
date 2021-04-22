package luj.cluster.api.actor;

import akka.actor.ActorRef;
import java.time.Duration;
import luj.cluster.api.logging.Log;

public interface ActorMessageHandler<A, M> {

  interface Context {

    <A> A getActorState(ActorMessageHandler<A, ?> handler);

    Ref getActorRef();

    <M> M getMessage(ActorMessageHandler<?, M> handler);

    Log getLogger();

    Ref getSenderRef();

    Node getRemoteNode();

    NodeLocal getLocalNode();

    ActorRef createActor(Object actorState);
  }

  interface Ref extends Tellable {

    void tell(Object msg, Duration delay);
  }

  interface NodeLocal {

    void shutdown();
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

  void onHandle(Context ctx) throws Exception;
}
