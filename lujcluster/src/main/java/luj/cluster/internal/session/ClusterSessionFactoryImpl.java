package luj.cluster.internal.session;

import luj.cluster.api.ClusterSession;
import luj.cluster.internal.node.ClusterNodeStarter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class ClusterSessionFactoryImpl implements ClusterSessionFactory {

  ClusterSessionFactoryImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public ClusterSession create() {
    InjectResult injectResult = collectSpringBeans();

    ClusterNodeStarter.Factory.create(injectResult.getStartListeners()).start();

    return null;
  }

  private InjectResult collectSpringBeans() {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_appContext);

      resultCtx.register(InjectConf.class);
      resultCtx.refresh();

      return resultCtx.getBean(InjectResult.class);
    }
  }

  private final ApplicationContext _appContext;
}
