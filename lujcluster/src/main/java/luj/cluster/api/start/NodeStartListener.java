package luj.cluster.api.start;

import akka.event.LoggingAdapter;

public interface NodeStartListener {

  interface Context {

    void sendMessage(Object msg);

    LoggingAdapter getLogger();
  }

  void onStart(Context ctx);
}
