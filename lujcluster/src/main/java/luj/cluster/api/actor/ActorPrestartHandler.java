package luj.cluster.api.actor;

public interface ActorPrestartHandler<A> {

  interface Context {

    <A> A getActor(ActorPrestartHandler<A> handler);

    Actor createActor(Object actorState);
  }

  interface Actor {

    void tell(Object msg);
  }

  void onHandle(Context ctx);
}
