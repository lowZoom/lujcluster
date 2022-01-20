package luj.cluster.internal.node.appactor.akka.root;

import static com.google.common.base.Preconditions.checkNotNull;

import akka.actor.ActorRef;
import akka.japi.pf.FI;
import luj.cluster.internal.node.appactor.akka.instance.message.MessageFromRemote;
import luj.cluster.internal.node.appactor.akka.root.message.AppRouteFromRemoteMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class OnAppRouteFromRemote implements FI.UnitApply<AppRouteFromRemoteMsg> {

  OnAppRouteFromRemote(AppRootAktor rootAktor) {
    _rootAktor = rootAktor;
  }

  @Override
  public void apply(AppRouteFromRemoteMsg i) {
    Class<?> appType = i.getActorType();
    ActorRef akkaRef = _rootAktor.getChildRefMap().get(appType);

    String actorName = appType.getName();
    MessageFromRemote remoteMsg = i.getMessage();
    String msgKey = remoteMsg.getMsgKey();

    checkNotNull(akkaRef, "%s(%s)", actorName, msgKey);
    LOG.debug("[cluster]投递远程消息：{} -> {}", msgKey, actorName);

    akkaRef.tell(remoteMsg, _rootAktor.context().sender());
  }

  private static final Logger LOG = LoggerFactory.getLogger(OnAppRouteFromRemote.class);

  private final AppRootAktor _rootAktor;
}
