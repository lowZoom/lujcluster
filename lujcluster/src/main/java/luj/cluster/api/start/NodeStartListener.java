package luj.cluster.api.start;

import akka.event.LoggingAdapter;
import java.util.Map;

public interface NodeStartListener {

  interface Context {

    void registerMessageHandler(Map<String, ?> handlerMap);

    void sendMessage(String msgKey, Object msg);

    LoggingAdapter getLogger();
  }

  void onStart(Context ctx);
}
