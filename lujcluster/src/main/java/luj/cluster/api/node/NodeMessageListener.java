package luj.cluster.api.node;

import akka.event.LoggingAdapter;

public interface NodeMessageListener {

  interface Context {

    Message getMessage();

    <T> T getApplicationBean();

    Actor getApplicationActor(Class<?> actorType);

    LoggingAdapter getLogger();

    void sendMessage(String msgKey, Object msg);
  }

  interface Message {

    String getId();

    Object getPayload();

    <T> T getHandler();
  }

  interface Actor {

    void tell(Object msg);
  }

  void onMessage(Context ctx);
}
