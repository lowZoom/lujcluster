package luj.game.internal.data.collect


import luj.game.api.data.PlayerDataLoad
import luj.game.internal.data.DataCmdEntry
import spock.lang.Specification

class DataCmdMapCollectorImplTest extends Specification {

  List playerCmdList

  List playerLoadList

  void setup() {
    // NOOP
  }

  def "Collect:"() {
    given:
    playerCmdList = [
        [TestCmd, TestResult],
    ]

    playerLoadList = [
        [TestResult, 'req'],
    ]

    when:
    def result = collect()

    then:
    result[TestCmd].requestor.toString() == 'req'
  }

  Map<Class, DataCmdEntry> collect() {
    return new DataCmdMapCollectorImpl(
        playerCmdList.collect { mockCmd(it as List) },
        playerLoadList.collect { mockLoad(it as List) },
    ).collect()
  }

  def mockCmd(List val) {
    def mock = Stub(DataCmdMapCollectorImpl.Cmd)
    mock.getCommandType() >> { val[0] }
    mock.getLoadResultType() >> { val[1] }
    return mock
  }

  def mockLoad(List val) {
    def mock = Stub(DataCmdMapCollectorImpl.Load)
    mock.getLoadResultType() >> { val[0] }
    mock.asReq() >> { [toString: { val[1] }] as PlayerDataLoad }
    return mock
  }

  private class TestResult {
    // NOOP
  }

  private class TestCmd {
    // NOOP
  }
}
