package luj.game.internal.luj.lujcluster.actor.datacache.execmd;

import java.util.Map;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.request.CacheRequest;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
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
    CacheRequest cacheReq = null;
    loadReq.load(null);

    CacheContainer dataCache = actor.getDataCache();
    dataCache.addRequest(cacheReq);
  }
}