package luj.cluster.api.actor;

public interface ActorPostStopHandler<A> {

  interface Context {

    <A> A getActorState(ActorPostStopHandler<A> handler);
  }

  void onHandle(Context ctx) throws Exception;
}
