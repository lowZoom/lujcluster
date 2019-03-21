package luj.cluster.internal.node.appactor.akka.instance.create;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import luj.cluster.internal.node.appactor.meta.ActorMetaMap;

public interface AppAktorCreator {

  interface Factory {

    static AppAktorCreator create(ActorMetaMap actorMetaMap,
        Class<?> actorType, Object actorState, ActorContext aktorCtx) {
      return new AppAktorCreatorImpl(actorMetaMap, actorType, actorState, aktorCtx);
    }
  }

  ActorRef create();
}
