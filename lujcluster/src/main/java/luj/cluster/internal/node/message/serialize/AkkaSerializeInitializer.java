package luj.cluster.internal.node.message.serialize;

import luj.cluster.internal.session.inject.ClusterBeanCollector;

public class AkkaSerializeInitializer {

  public AkkaSerializeInitializer(ClusterBeanCollector.Result beanCollect, Object appBean) {
    _beanCollect = beanCollect;
    _appBean = appBean;
  }

  public void init() {
    AkkaMessageSerializer.sApplicationBean = _appBean;
    AkkaMessageSerializer.sTypeResolver = _beanCollect.getMessageTypeResolver();

    AkkaMessageSerializer.sSerializerMap =
        new MessageSerializerCollector(_beanCollect.getNodeMessageSerializers()).collect();
  }

  private final ClusterBeanCollector.Result _beanCollect;

  private final Object _appBean;
}
