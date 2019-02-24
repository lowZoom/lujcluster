package luj.cluster.internal.session.inject;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.message.NodeMessageListener;
import luj.cluster.api.start.NodeStartListener;
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

  private <T> List<T> nonnull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<NodeStartListener> _startListeners;

  @Autowired
  private NodeMessageListener _messageListener;
}