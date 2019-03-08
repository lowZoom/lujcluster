package luj.cluster.api.actor;

public interface ActorMessageHandler<A, M> {

  interface Context {

    <A> A getActor(ActorMessageHandler<A, ?> handler);

    <M> M getMessage(ActorMessageHandler<?, M> handler);
  }

  void onHandle(Context ctx);
}
