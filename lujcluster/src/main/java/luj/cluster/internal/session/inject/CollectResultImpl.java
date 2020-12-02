package luj.cluster.internal.session.inject;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPostStopHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.node.NodeMessageSerializer;
import luj.cluster.api.node.NodeNewMemberListener;
import luj.cluster.api.node.NodeStartListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class CollectResultImpl implements ClusterBeanCollector.Result {

  @Override
  public List<NodeStartListener> getNodeStartListeners() {
    return nonNull(_nodeStartListeners);
  }

  @Override
  public NodeNewMemberListener getNodeJoinListener() {
    return _nodeJoinListener;
  }

  @Override
  public List<NodeMessageSerializer<?>> getNodeMessageSerializers() {
    return nonNull(_nodeMessageSerializers);
  }

  @Override
  public List<ActorPreStartHandler<?>> getActorPreStartHandlers() {
    return nonNull(_actorPreStartHandlers);
  }

  @Override
  public List<ActorPostStopHandler<?>> getActorPostStopHandlers() {
    return nonNull(_actorPostStopHandlers);
  }

  @Override
  public List<ActorMessageHandler<?, ?>> getActorMessageHandlers() {
    return nonNull(_actorMessageHandlers);
  }

  static <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<NodeStartListener> _nodeStartListeners;

  @Autowired(required = false)
  private NodeNewMemberListener _nodeJoinListener;

  @Autowired(required = false)
  private List<NodeMessageSerializer<?>> _nodeMessageSerializers;

  @Autowired(required = false)
  private List<ActorPreStartHandler<?>> _actorPreStartHandlers;

  @Autowired(required = false)
  private List<ActorPostStopHandler<?>> _actorPostStopHandlers;

  @Autowired(required = false)
  private List<ActorMessageHandler<?, ?>> _actorMessageHandlers;
}
