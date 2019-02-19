package luj.cluster.internal.session;

import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.start.NodeStartListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class InjectResult {

  List<NodeStartListener> getStartListeners() {
    return _startListeners;
  }

  @Autowired
  private List<NodeStartListener> _startListeners;
}
