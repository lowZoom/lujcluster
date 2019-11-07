package luj.cluster.internal.node.start.actor;

import akka.actor.ActorRef;
import akka.event.LoggingAdapter;
import java.util.Map;
import luj.cluster.api.node.NodeStartListener;
import luj.cluster.internal.node.appactor.akka.root.message.CreateAppActorMsg;
import luj.cluster.internal.node.message.receive.message.RegisterReceiveMsg;
import luj.cluster.internal.node.message.send.actor.message.NodeSendStartMsg;

final class StartContextImpl implements NodeStartListener.Context {

  StartContextImpl(ActorRef receiveRef, ActorRef sendRef,
      ActorRef appRootRef, NodeStartAktor startActor, LoggingAdapter logger) {
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
  public NodeStartListener.Actor createApplicationActor(Object actorState) {
    Class<?> actorType = actorState.getClass();
    _appRootRef.tell(new CreateAppActorMsg(actorType, actorState), ActorRef.noSender());
    return new ActorImpl(_appRootRef, actorType);
  }

  /**
   * @see luj.cluster.internal.node.message.send.actor.OnNodeSend#apply
   */
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

  private final NodeStartAktor _startActor;
  private final LoggingAdapter _logger;
}
