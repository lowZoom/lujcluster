package luj.game.internal.data;

import java.util.List;
import luj.game.api.data.PlayerDataCommand;
import luj.game.api.data.PlayerDataLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataCmdCollectBean {

  public List<PlayerDataCommand<?>> getPlayerDataCmdList() {
    return _playerDataCmdList;
  }

  public List<PlayerDataLoad<?>> getPlayerDataReqList() {
    return _playerDataReqList;
  }

  @Autowired(required = false)
  private List<PlayerDataCommand<?>> _playerDataCmdList;

  @Autowired(required = false)
  private List<PlayerDataLoad<?>> _playerDataReqList;
}
