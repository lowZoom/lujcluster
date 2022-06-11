package luj.cluster.internal.node.start.actor.trigger;

import akka.actor.ActorRef;
import java.util.List;
import luj.cluster.api.node.NodeStartListener;

public class StartListenTrigger {

  public StartListenTrigger(ActorRef receiveRef, ActorRef sendRef, ActorRef appRootRef,
      Object startParam, List<NodeStartListener> listenerList) {
    _receiveRef = receiveRef;
    _sendRef = sendRef;
    _appRootRef = appRootRef;
    _startParam = startParam;
    _listenerList = listenerList;
  }

  public void trigger() throws Exception {
    StartContextImpl context = new StartContextImpl(
        _receiveRef, _sendRef, _appRootRef, _startParam, null);

    for (NodeStartListener listener : _listenerList) {
      listener.onStart(context);
    }
  }

  private final ActorRef _receiveRef;
  private final ActorRef _sendRef;
  private final ActorRef _appRootRef;

  private final Object _startParam;
  private final List<NodeStartListener> _listenerList;
}
