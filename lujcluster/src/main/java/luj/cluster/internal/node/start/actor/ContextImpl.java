package luj.cluster.internal.node.start.actor;

import akka.actor.ActorRef;
import akka.event.LoggingAdapter;
import java.util.Map;
import luj.cluster.api.start.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.send.message.NodeSendStartMsg;

final class ContextImpl implements NodeStartListener.Context {

  ContextImpl(ActorRef receiveRef, ActorRef sendRef,
      ActorRef appRootRef, NodeStartActor startActor, LoggingAdapter logger) {
    _receiveRef = receiveRef;
    _sendRef = sendRef;

    _appRootRef = appRootRef;

    _startActor = startActor;
    _logger = logger;
  }

  /**
   * @see luj.cluster.internal.node.message.receive.actor.OnRegisterReceive#apply
   */
  @Override
  public void registerMessageHandler(Map<String, ?> handlerMap) {
    _receiveRef.tell(new RegisterReceiveMsg(handlerMap), ActorRef.noSender());
  }

  @Override
  public void createApplicationActor(Object actorState) {
    _appRootRef.tell(new CreateAppActorMsg(actorState.getClass(), actorState), ActorRef.noSender());
  }

  @Override
  public void sendMessage(String msgKey, Object msg) {
    _sendRef.tell(new NodeSendStartMsg(msgKey, msg), ActorRef.noSender());
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getStartParam() {
    return (T) _startActor.getStartParam();
  }

  @Override
  public LoggingAdapter getLogger() {
    return _logger;
  }

  private final ActorRef _receiveRef;
  private final ActorRef _sendRef;

  private final ActorRef _appRootRef;

  private final NodeStartActor _startActor;
  private final LoggingAdapter _logger;
}
