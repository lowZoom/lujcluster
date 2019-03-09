package luj.game.internal.luj.cluster.data;

import java.util.Map;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;
import luj.game.internal.luj.cluster.data.loadreq.RequestExecPlayerCmdMsg;
import org.springframework.stereotype.Component;

@Component
final class OnRequestExecPlayerCmd implements DataActorMsgHandler<RequestExecPlayerCmdMsg> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState actor = ctx.getActor(this);

    RequestExecPlayerCmdMsg msg = ctx.getMessage(this);

    String playerId = msg.getPlayerId();
    Class<?> cmdType = msg.getCmdType();

    //TODO: 取出对应CMD的取数据逻辑并调用
    Map<Class<?>, DataCmdEntry> cmdMap = actor.getCommandMap();
    DataCmdEntry cmdEntry = cmdMap.get(cmdType);

    PlayerDataLoad<?> loadReq = cmdEntry.getRequestor();
    loadReq.load(null);
  }
}
