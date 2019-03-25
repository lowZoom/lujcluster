package luj.cluster.internal.session.inject;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.node.NodeMessageListener;
import luj.cluster.api.node.NodeStartListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class CollectResultImpl implements ClusterBeanCollector.Result {

  @Override
  public List<NodeStartListener> getStartListeners() {
    return nonnull(_startListeners);
  }

  @Override
  public NodeMessageListener getMessageListener() {
    return _messageListener;
  }

  @Override
  public List<ActorPreStartHandler<?>> getActorPreStartHandlers() {
    return _actorPreStartHandlers;
  }

  @Override
  public List<ActorMessageHandler<?, ?>> getActorMessageHandlers() {
    return nonnull(_actorMessageHandlers);
  }

  private <T> List<T> nonnull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<NodeStartListener> _startListeners;

  @Autowired
  private NodeMessageListener _messageListener;

  @Autowired(required = false)
  private List<ActorPreStartHandler<?>> _actorPreStartHandlers;

  @Autowired(required = false)
  private List<ActorMessageHandler<?, ?>> _actorMessageHandlers;
}
