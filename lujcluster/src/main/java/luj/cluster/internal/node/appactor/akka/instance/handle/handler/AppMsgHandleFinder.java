package luj.cluster.internal.node.appactor.akka.instance.handle.handler;

import akka.actor.ActorContext;
import akka.actor.ActorRef;
import luj.cluster.api.actor.ActorMessageHandler;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;
import luj.cluster.internal.node.appactor.message.handle.ActorMessageHandleMap;

public class AppMsgHandleFinder {

  public interface Handler {

    boolean isAbsent();

    void handleAbsent();

    ActorMessageHandler<?, ?> get();
  }

  public AppMsgHandleFinder(AppAktor actor, String msgKey, Object appMsg) {
    _actor = actor;
    _msgKey = msgKey;
    _appMsg = appMsg;
  }

  public Handler find() {
    ActorMessageHandleMap handlerMap = _actor.getMeta().getMessageHandleMap();
    ActorMessageHandler<?, ?> handler = handlerMap.getHandler(_msgKey);

    return makeResult(handler);
  }

  private Handler makeResult(ActorMessageHandler<?, ?> handler) {
    ActorContext ctx = _actor.context();
    ActorRef senderRef = ctx.sender();

    return new Handler() {
      @Override
      public boolean isAbsent() {
        return handler == null;
      }

      @Override
      public void handleAbsent() {
        ctx.self().tell(_appMsg, senderRef);
      }

      @Override
      public ActorMessageHandler<?, ?> get() {
        return handler;
      }
    };
  }

  private final AppAktor _actor;

  private final String _msgKey;
  private final Object _appMsg;
}
