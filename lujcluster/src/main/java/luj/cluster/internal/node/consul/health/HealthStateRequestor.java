package luj.cluster.internal.node.consul.health;

import static java.util.stream.Collectors.toMap;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.Check;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class HealthStateRequestor {

  public interface Result {

    Long consulIndex();

    Map<String, Check> serviceMap();
  }

  public static Result nullResult() {
    return new Result() {
      @Override
      public Long consulIndex() {
        return null;
      }

      @Override
      public Map<String, Check> serviceMap() {
        return ImmutableMap.of();
      }
    };
  }

  public HealthStateRequestor(ConsulClient consul, QueryParams params, String selfName) {
    _consul = consul;
    _params = params;
    _selfName = selfName;
  }

  public Result request() {
    Response<List<Check>> rsp = _consul.getHealthChecksState(ANY, _params);
//    for (Check check : rsp.getValue()) {
//      LOG.debug("{}", check);
//    }

    Map<String, Check> serviceMap = rsp.getValue().stream()
        .filter(c -> !c.getServiceId().isEmpty())
        .filter(c -> !_selfName.equals(c.getServiceName()))
        .collect(toMap(Check::getServiceName, Function.identity()));

    Long consulIndex = rsp.getConsulIndex();
    return new Result() {
      @Override
      public Long consulIndex() {
        return consulIndex;
      }

      @Override
      public Map<String, Check> serviceMap() {
        return serviceMap;
      }
    };
  }

//  private static final Logger LOG = LoggerFactory.getLogger(HealthStateRequestor.class);
  private static final Check.CheckStatus ANY = null;

  private final ConsulClient _consul;
  private final QueryParams _params;

  private final String _selfName;
}
