package luj.cache.internal.session.inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

final class CacheBeanCollectorImpl implements CacheBeanCollector {

  CacheBeanCollectorImpl(ApplicationContext appContext) {
    _appContext = appContext;
  }

  @Override
  public Result collect() {
    try (AnnotationConfigApplicationContext resultCtx = new AnnotationConfigApplicationContext()) {
      resultCtx.setParent(_appContext);

      resultCtx.register(CollectResultImpl.class);
      resultCtx.refresh();

      return resultCtx.getBean(CollectResultImpl.class);
    }
  }

  private final ApplicationContext _appContext;
}
