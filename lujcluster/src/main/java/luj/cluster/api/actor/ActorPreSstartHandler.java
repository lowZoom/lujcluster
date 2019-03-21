package luj.cluster.api.actor;

public interface ActorPreSstartHandler<A> {

  interface Context {

    <A> A getActorState(ActorPreSstartHandler<A> handler);

    Actor getActor();

    Actor createActor(Object actorState);
  }

  interface Actor {

    void tell(Object msg);
  }

  void onHandle(Context ctx);
}
