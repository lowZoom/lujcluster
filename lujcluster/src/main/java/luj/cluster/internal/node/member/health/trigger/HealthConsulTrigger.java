package luj.cluster.internal.node.member.health.trigger;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.health.model.Check;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HealthConsulTrigger {

  public HealthConsulTrigger(NodeMemberHealthListener healthListener, Check serviceCheck,
      ActorRef receiveRef, ActorRef memberRef) {
    _healthListener = healthListener;
    _serviceCheck = serviceCheck;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
  }

  public void trigger() {
    if (_healthListener == null) {
      return;
    }

    NodeLocalImpl selfNode = new NodeLocalImpl();
    selfNode._receiveRef = _receiveRef;
    selfNode._memberRef = _memberRef;

    NodeRemoteImpl memberNode = new NodeRemoteImpl();
    memberNode._serviceCheck = _serviceCheck;

    HealthContextImpl ctx = new HealthContextImpl();
    ctx._selfNode = selfNode;
    ctx._memberNode = memberNode;

    try {
      _healthListener.onHealth(ctx);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private static final Logger LOG = LoggerFactory.getLogger(HealthConsulTrigger.class);

  private final NodeMemberHealthListener _healthListener;
  private final Check _serviceCheck;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
