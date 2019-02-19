package luj.cluster.api.message;

import akka.event.LoggingAdapter;

public interface NodeMessageHandler<M> {

  interface Context<M> {

    M getMessage();

    LoggingAdapter getLogger();
  }

  void onHandle(Context<M> ctx);
}
