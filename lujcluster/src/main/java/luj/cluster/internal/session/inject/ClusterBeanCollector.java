package luj.cluster.internal.session.inject;

import java.util.List;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.node.NodeShutdownListener;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.api.node.NodeStartListener;
import luj.cluster.api.node.message.MessageValueResolver;
import luj.cluster.api.node.message.NodeMessageSerializer;
import org.springframework.context.ApplicationContext;

public interface ClusterBeanCollector {

  interface Factory {

    static ClusterBeanCollector create(ApplicationContext appContext) {
      return new ClusterBeanCollectorImpl(appContext);
    }
  }

  interface Result {

    List<NodeStartListener> getNodeStartListeners();

    List<NodeShutdownListener> getNodeShutdownListeners();

    NodeNewMemberListener getNodeJoinListener();

    NodeMemberHealthListener getNodeHealthListener();

    MessageValueResolver getMessageTypeResolver();

    List<NodeMessageSerializer<?>> getNodeMessageSerializers();

    List<ActorPreStartHandler<?>> getActorPreStartHandlers();

    List<ActorPostStopHandler<?>> getActorPostStopHandlers();

    List<ActorMessageHandler<?, ?>> getActorMessageHandlers();
  }

  Result collect();
}
