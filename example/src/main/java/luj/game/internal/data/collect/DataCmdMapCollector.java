package luj.game.internal.data.collect;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import luj.game.internal.data.DataCmdCollectBean;
import luj.game.internal.data.DataCmdEntry;

public interface DataCmdMapCollector {

  interface Factory {

    static DataCmdMapCollector create(DataCmdCollectBean collectBean) {
      List<DataCmdMapCollectorImpl.Cmd> cmdList = collectBean.getPlayerDataCmdList().stream()
          .map(c -> new CmdImpl(c, c.getClass()))
          .collect(Collectors.toList());

      List<DataCmdMapCollectorImpl.Load> loadList = collectBean.getPlayerDataReqList().stream()
          .map(LoadImpl::new)
          .collect(Collectors.toList());

      return new DataCmdMapCollectorImpl(cmdList, loadList);
    }
  }

  Map<Class<?>, DataCmdEntry> collect();
}
