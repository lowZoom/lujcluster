package luj.cluster.internal.node.appactor.akka.root;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnAppRoute implements FI.UnitApply<AppRouteMsg> {

  OnAppRoute(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  /**
   * @see luj.cluster.internal.node.appactor.akka.instance.AppAktor
   */
  @Override
  public void apply(AppRouteMsg i) {
    Class<?> appType = i.getActorType();
    ActorRef akkaRef = _rootAktor.getChildRefMap().get(appType);

    Object msg = i.getAppMsg();
    Class<?> msgType = msg.getClass();

    String actorName = appType.getName();
    checkNotNull(akkaRef, "%s(%s)", actorName, msgType.getName());

    LOG.debug("[cluster]投递应用消息：{}{} -> {}",
        msgType.getSimpleName(), msgType.getInterfaces(), actorName);

    akkaRef.tell(msg, _rootAktor.sender());
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnAppRoute.class);

  private final AppRootAktor _rootAktor;
}
