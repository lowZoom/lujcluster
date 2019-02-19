package luj.cluster.internal.node.actor;

import akka.event.LoggingAdapter;
import luj.cluster.api.start.NodeStartListener;
import org.omg.CORBA.NO_IMPLEMENT;

final class ContextImpl implements NodeStartListener.Context {

  ContextImpl(LoggingAdapter logger) {
    _logger = logger;
  }

  @Override
  public void sendMessage(Object msg) {
    throw new NO_IMPLEMENT("sendMessage尚未实现");
  }

  @Override
  public LoggingAdapter getLogger() {
    return _logger;
  }

  private final LoggingAdapter _logger;
}
