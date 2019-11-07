package luj.cluster.api.node;

import akka.event.LoggingAdapter;
import java.util.Map;

public interface NodeStartListener {

  interface Context {

    void registerMessageHandler(Map<String, ?> handlerMap);

    Actor createApplicationActor(Object actorState);

    void sendMessage(String msgKey, Object msg);

    <T> T getStartParam();

    LoggingAdapter getLogger();
  }

  interface Actor {

    void tell(Object msg);
  }

  void onStart(Context ctx) throws Exception;
}
