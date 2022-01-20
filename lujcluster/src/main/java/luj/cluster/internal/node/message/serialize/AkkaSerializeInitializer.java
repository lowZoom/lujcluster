package luj.cluster.internal.node.message.serialize;

import java.util.Map;
import luj.cluster.api.node.message.NodeMessageSerializer;
import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class AkkaSerializeInitializer {

  public AkkaSerializeInitializer(ClusterBeanCollector.Result beanCollect,
      Map<String, NodeMessageSerializer<?>> msgCodecMap, Object appBean) {
    _beanCollect = beanCollect;
    _msgCodecMap = msgCodecMap;
    _appBean = appBean;
  }

  public void init() {
    AkkaMessageSerializer.sTypeResolver = _beanCollect.getMessageTypeResolver();
    AkkaMessageSerializer.sSerializerMap = _msgCodecMap;
    AkkaMessageSerializer.sApplicationBean = _appBean;
  }

  private final ClusterBeanCollector.Result _beanCollect;
  private final Map<String, NodeMessageSerializer<?>> _msgCodecMap;

  private final Object _appBean;
}
