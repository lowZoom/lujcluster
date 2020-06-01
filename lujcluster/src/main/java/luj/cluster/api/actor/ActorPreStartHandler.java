package luj.cluster.api.actor;

import akka.actor.ActorRef;

public interface ActorPreStartHandler<A> {

  interface Context {

    <A> A getActorState(ActorPreStartHandler<A> handler);

    Actor getActor();

    Actor createActor(Object actorState);
  }

  interface Actor extends Tellable {

    ActorRef getAkkaRef();
  }

  void onHandle(Context ctx) throws Exception;
}
