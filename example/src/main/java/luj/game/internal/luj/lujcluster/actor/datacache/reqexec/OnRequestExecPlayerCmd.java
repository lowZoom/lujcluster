package luj.game.internal.luj.lujcluster.actor.datacache.reqexec;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.request.CacheRequest;
import luj.cache.internal.request.CacheRequestImpl;
import luj.cluster.example.module.scene.data.SceneObjectDat;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;
import luj.game.internal.luj.lujcache.JamreqInLujcache;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import org.springframework.stereotype.Component;

@Internal
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
//    loadReq.load(null);

    //FIXME: TEMP
    cacheReq = new CacheRequestImpl();
    cacheReq.addNode(SceneObjectDat.class, "123");

    CacheContainer dataCache = actor.getDataCache();
    dataCache.request(cacheReq, new JamreqInLujcache(, cmdEntry.getCommand()));
  }
}
