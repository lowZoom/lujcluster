package luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.reqexec;

import java.util.Map;
import luj.ava.spring.Internal;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.request.CacheRequest;
import luj.cache.internal.request.CacheRequestImpl;
import luj.cluster.api.logging.Log;
import luj.cluster.example.module.scene.control.login.SceneLoginCmdLoadResult;
import luj.cluster.example.module.scene.data.SceneObjectDat;
import luj.game.api.data.PlayerDataLoad;
import luj.game.internal.data.DataCmdEntry;
import luj.game.internal.luj.lujcache.JamreqInLujcache;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorState;

@Internal
final class OnRequestExecPlayerCmd implements DataActorMsgHandler<RequestExecPlayerCmdMsg> {

  @Override
  public void onHandle(Context ctx) {
    Log log = ctx.getLogger();
    RequestExecPlayerCmdMsg msg = ctx.getMessage(this);

    Class<?> cmdType = msg.getCmdType();
    log.debug("新的数据cmd：{}", cmdType.getSimpleName());

    DataActorState actor = ctx.getActor(this);
    String playerId = msg.getPlayerId();

    //TODO: 取出对应CMD的取数据逻辑并调用
    Map<Class<?>, DataCmdEntry> cmdMap = actor.getCommandMap();
    DataCmdEntry cmdEntry = cmdMap.get(cmdType);

    PlayerDataLoad<?> loadReq = cmdEntry.getRequestor();
    CacheRequest cacheReq = null;
//    loadReq.load(null);

    //FIXME: TEMP
    cacheReq = new CacheRequestImpl(SceneLoginCmdLoadResult.class);
    cacheReq.addNode(SceneObjectDat.class, "123", SceneLoginCmdLoadResult::setSceneObject);

    CacheContainer dataCache = actor.getDataCache();
    dataCache.request(cacheReq, new JamreqInLujcache(actor, cmdEntry.getCommand(), log));
  }
}
