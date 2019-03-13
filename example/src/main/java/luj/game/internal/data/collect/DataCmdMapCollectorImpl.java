package luj.game.internal.data.collect;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;

final class DataCmdMapCollectorImpl implements DataCmdMapCollector {

  DataCmdMapCollectorImpl(List<Cmd> cmdList, List<Load> loadList) {
    _cmdList = cmdList;
    _loadList = loadList;
  }

  @Override
  public Map<Class<?>, DataCmdEntry> collect() {
    Map<Class<?>, Load> loadMap = _loadList.stream()
        .collect(Collectors.toMap(Load::getLoadResultType, Function.identity()));

    return _cmdList.stream()
        .collect(Collectors.toMap(Cmd::getCommandType, c -> createCmdEntry(c, loadMap)));
  }

  private DataCmdEntry createCmdEntry(Cmd cmd, Map<Class<?>, Load> loadMap) {
    Load load = loadMap.get(cmd.getLoadResultType());
    return new DataCmdEntryImpl(load.asReq());
  }

  interface Cmd {

    Class<?> getCommandType();

    Class<?> getLoadResultType();
  }

  interface Load {

    Class<?> getLoadResultType();

    PlayerDataLoad<?> asReq();
  }

  private final List<Cmd> _cmdList;

  private final List<Load> _loadList;
}
