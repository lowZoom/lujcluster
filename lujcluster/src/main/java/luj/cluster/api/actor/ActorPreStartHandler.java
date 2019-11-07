package luj.cluster.api.actor;

import akka.actor.ActorRef;

public interface ActorPreStartHandler<A> {

  interface Context {

    <A> A getActorState(ActorPreStartHandler<A> handler);

    Actor getActor();

    ActorRef getActorRef();

    Actor createActor(Object actorState);
  }

  interface Actor {

    void tell(Object msg);
  }

  void onHandle(Context ctx);
}
