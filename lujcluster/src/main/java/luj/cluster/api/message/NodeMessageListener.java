package luj.cluster.api.message;

import akka.event.LoggingAdapter;

public interface NodeMessageListener {

  interface Context {

    Message getMessage();

    LoggingAdapter getLogger();
  }

  interface Message {

    String getId();

    Object getPayload();

    <T> T getHandler();
  }

  void onMessage(Context ctx);
}
