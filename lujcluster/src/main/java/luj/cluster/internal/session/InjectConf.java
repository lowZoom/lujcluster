package luj.cluster.internal.session;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.cluster.internal.session",
}, includeFilters = {
    @ComponentScan.Filter(Internal.class),
})
final class InjectConf {
  // NOOP
}
