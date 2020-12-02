package luj.cluster.internal.session.inject;

import java.util.List;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.node.NodeMessageSerializer;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.api.node.NodeStartListener;
import org.springframework.context.ApplicationContext;

public interface ClusterBeanCollector {

  interface Factory {

    static ClusterBeanCollector create(ApplicationContext appContext) {
      return new ClusterBeanCollectorImpl(appContext);
    }
  }

  interface Result {

    List<NodeStartListener> getNodeStartListeners();

    NodeNewMemberListener getNodeJoinListener();

    List<NodeMessageSerializer<?>> getNodeMessageSerializers();

    List<ActorPreStartHandler<?>> getActorPreStartHandlers();

    List<ActorPostStopHandler<?>> getActorPostStopHandlers();

    List<ActorMessageHandler<?, ?>> getActorMessageHandlers();
  }

  Result collect();
}
