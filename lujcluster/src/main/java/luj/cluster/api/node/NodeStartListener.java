package luj.cluster.api.node;

import akka.event.LoggingAdapter;
import java.util.Map;
import luj.cluster.api.actor.Tellable;

public interface NodeStartListener {

  interface Context {

    void registerMessageHandler(Map<String, ?> handlerMap);

    Actor createApplicationActor(Object actorState);

    void sendMessage(String msgKey, Object msg);

    <T> T getStartParam();

    LoggingAdapter getLogger();
  }

  interface Actor extends Tellable {
    //TODO: 暂时没有，后面应该会有
  }

  void onStart(Context ctx) throws Exception;
}
