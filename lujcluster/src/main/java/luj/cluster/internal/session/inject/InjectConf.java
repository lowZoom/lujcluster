package luj.cluster.internal.session.inject;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.cluster.internal.session.inject",
}, includeFilters = {
    @ComponentScan.Filter(luj.ava.spring.Internal.class),
    @ComponentScan.Filter(luj.spring.anno.Internal.class),
})
final class InjectConf {
  // NOOP
}
