package luj.game.internal.start;

import luj.ava.spring.Internal;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {
    "luj.game.internal",
}, includeFilters = {
    @ComponentScan.Filter(Internal.class),
})
final class InjectConf {
  // NOOP
}
