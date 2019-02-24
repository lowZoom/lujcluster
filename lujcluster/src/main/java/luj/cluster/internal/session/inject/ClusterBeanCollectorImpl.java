package luj.cluster.internal.session.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class ClusterBeanCollectorImpl implements ClusterBeanCollector {

  ClusterBeanCollectorImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public Result collect() {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_appContext);

      resultCtx.register(InjectConf.class);
      resultCtx.refresh();

      return resultCtx.getBean(CollectResultImpl.class);
    }
  }

  private final ApplicationContext _appContext;
}
