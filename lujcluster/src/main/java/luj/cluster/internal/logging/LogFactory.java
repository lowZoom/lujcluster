package luj.cluster.internal.logging;

import akka.event.Logging;
import luj.cluster.api.logging.Log;
import luj.cluster.internal.node.appactor.akka.instance.AppAktor;

public interface LogFactory {

  static Log get(AppAktor aktor, Object logSource) {
    return new LogImpl(Logging.getLogger(aktor));//.context().system(), logSource));
  }
}
