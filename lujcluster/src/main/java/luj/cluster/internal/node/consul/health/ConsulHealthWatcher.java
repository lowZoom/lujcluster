package luj.cluster.internal.node.consul.health;

import static java.util.concurrent.Executors.newCachedThreadPool;

import akka.actor.ActorRef;
import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.Check;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.internal.node.member.health.trigger.HealthConsulTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsulHealthWatcher {

  public ConsulHealthWatcher(ConsulClient consul, NodeMemberHealthListener healthListener,
      ActorRef receiveRef, ActorRef memberRef) {
    _consul = consul;
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
    Long lastIndex = triggerHealth(null, QueryParams.DEFAULT);

    while (!EXEC.isShutdown()) {
      lastIndex = triggerHealth(lastIndex, QueryParams.Builder.builder()
          .setIndex(lastIndex)
          .build());
    }
  }

  private Long triggerHealth(Long lastIndex, QueryParams params) {
    LOG.debug("sssssssssssssssssstart------------------");

    Response<List<Check>> rsp = _consul.getHealthChecksState(ANY, params);
    Long newIndex = rsp.getConsulIndex();

    List<Check> checkList = rsp.getValue().stream()
        .filter(c -> !c.getServiceId().isEmpty())
        .collect(Collectors.toList());

    LOG.debug("eeeeeeeeeeeeeeeeeeeeeeeeeeend------------------ ");
//    for (Check check : checkList) {
//      LOG.debug("{}", check);
//    }

    if (newIndex.equals(lastIndex)) {
      return lastIndex;
    }

    for (Check check : checkList) {
      new HealthConsulTrigger(_healthListener, check, _receiveRef, _memberRef).trigger();
    }
    return newIndex;
  }

  private static final Logger LOG = LoggerFactory.getLogger(ConsulHealthWatcher.class);
  private static final Check.CheckStatus ANY = null;

  private static final ExecutorService EXEC = newCachedThreadPool(new ThreadFactoryBuilder()
      .setNameFormat("consul-watch-%d")
      .build());

  private final ConsulClient _consul;
  private final NodeMemberHealthListener _healthListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
