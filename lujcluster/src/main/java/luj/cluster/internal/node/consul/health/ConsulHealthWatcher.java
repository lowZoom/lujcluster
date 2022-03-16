package luj.cluster.internal.node.consul.health;

import static java.util.concurrent.Executors.newCachedThreadPool;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.health.model.Check;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.internal.node.member.health.diff.HealthChangeComparer;
import luj.cluster.internal.node.member.health.trigger.HealthConsulTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsulHealthWatcher {

  public ConsulHealthWatcher(ConsulClient consul, String selfName,
      NodeMemberHealthListener healthListener, ActorRef receiveRef, ActorRef memberRef) {
    _consul = consul;
    _selfName = selfName;
    _healthListener = healthListener;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
  }

  public void watch() {
    EXEC.submit(this::watchWrap);
  }

  private void watchWrap() {
    try {
      watchImpl();
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
    }
  }

  private void watchImpl() {
    HealthStateRequestor.Result lastRsp = triggerHealth(
        HealthStateRequestor.nullResult(), QueryParams.DEFAULT);

    while (!EXEC.isShutdown()) {
      lastRsp = triggerHealth(lastRsp, QueryParams.Builder.builder()
          .setIndex(lastRsp.consulIndex())
          .build());
    }
  }

  private HealthStateRequestor.Result triggerHealth(HealthStateRequestor.Result lastRsp,
      QueryParams params) {
    LOG.debug("sssssssssssssssssstart------------------");

    HealthStateRequestor.Result newRsp =
        new HealthStateRequestor(_consul, params, _selfName).request();

    Long newIndex = newRsp.consulIndex();
    LOG.debug("eeeeeeeeeeeeeeeeeeeeeeeeeeend------------------ {}", newIndex);

    if (newIndex.equals(lastRsp.consulIndex())) {
      return newRsp;
    }

    List<Check> diffList = new HealthChangeComparer(
        lastRsp.serviceMap(), newRsp.serviceMap()).diff();

    for (Check check : diffList) {
      LOG.debug("节点健康变化：{}", check);
      new HealthConsulTrigger(_healthListener, check, _receiveRef, _memberRef).trigger();
    }

    return newRsp;
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConsulHealthWatcher.class);

  private static final ExecutorService EXEC = newCachedThreadPool(new ThreadFactoryBuilder()
      .setNameFormat("consul-watch-%d")
      .build());

  private final ConsulClient _consul;

  private final String _selfName;
  private final NodeMemberHealthListener _healthListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
