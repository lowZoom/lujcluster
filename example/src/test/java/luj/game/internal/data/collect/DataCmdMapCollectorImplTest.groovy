package luj.game.internal.data.collect


import luj.game.internal.data.DataCmdEntry
import spock.lang.Specification

class DataCmdMapCollectorImplTest extends Specification {
  void setup() {
  }

  def "Collect:"() {
    when:
    def result = collect()

    given:
    false
  }

  Map<Class, DataCmdEntry> collect() {
    return new DataCmdMapCollectorImpl().collect()
  }
}
