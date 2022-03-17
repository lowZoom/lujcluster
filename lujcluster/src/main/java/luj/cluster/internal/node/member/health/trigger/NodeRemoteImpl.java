package luj.cluster.internal.node.member.health.trigger;

import com.ecwid.consul.v1.health.model.Check;
import java.util.List;
import luj.cluster.api.node.member.NodeMemberHealthListener;

final class NodeRemoteImpl implements NodeMemberHealthListener.RemoteNode {

  @Override
  public String getId() {
    return _serviceCheck.getServiceName();
  }

  @Override
  public List<String> getTags() {
    return _serviceCheck.getServiceTags();
  }

  @Override
  public boolean isHealthy() {
    return _serviceCheck.getStatus() == Check.CheckStatus.PASSING;
  }

  @Override
  public String toString() {
    return _serviceCheck.toString();
  }

  Check _serviceCheck;
}
