package luj.cluster.internal.node.member.health.diff;

import com.ecwid.consul.v1.health.model.Check;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import scala.Tuple2;

public class HealthChangeComparer {

  public HealthChangeComparer(Map<String, Check> oldResult, Map<String, Check> newResult) {
    _oldResult = oldResult;
    _newResult = newResult;
  }

  public List<Check> diff() {
    return _newResult.entrySet().stream()
        .map(e -> new Tuple2<>(_oldResult.get(e.getKey()), e.getValue()))
        .map(t -> keepChanged(t._1, t._2))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  private Check keepChanged(Check svcOld, Check svcNew) {
    if (svcOld == null) {
      return svcNew;
    }
    if (svcOld.getStatus() == svcNew.getStatus()) {
      return null;
    }
    return svcNew;
  }

  private final Map<String, Check> _oldResult;

  private final Map<String, Check> _newResult;
}
