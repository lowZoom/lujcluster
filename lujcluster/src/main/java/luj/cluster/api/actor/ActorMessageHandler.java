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

    //FIXME: 本地actor可互发，这个概念不存在
    @Deprecated
    Node getRemoteNode();

    Ref getSenderRef();

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

    void tell(Object msg);

    void tell(Object msg, Duration delay);
  }

  void onHandle(Context ctx) throws Exception;
}
