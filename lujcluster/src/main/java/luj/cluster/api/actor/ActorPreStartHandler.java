package luj.cluster.api.actor;

public interface ActorPreStartHandler<A> {

  interface Context {

    <A> A getActorState(ActorPreStartHandler<A> handler);

    Actor getActor();

    Actor createActor(Object actorState);
  }

  interface Actor {

    void tell(Object msg);
  }

  void onHandle(Context ctx) throws Exception;
}
