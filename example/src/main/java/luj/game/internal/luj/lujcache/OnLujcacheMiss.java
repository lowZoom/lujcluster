package luj.game.internal.luj.lujcache;

import luj.ava.spring.Internal;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheKey;
import luj.cache.api.listener.CacheMissListener;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.load.loadreq.RequestLoadDataMsg;

@Internal
final class OnLujcacheMiss implements CacheMissListener {

  @Override
  public void onMiss(Context ctx) {
    JamreqInLujcache param = ctx.getRequestParam();
    Log log = param.getLogger();

    CacheKey key = ctx.getMissingKey();
    log.debug("缓存项miss，key：{}", key);

    //TODO: 添加缓存项 占位
    CacheContainer container = ctx.getCacheContainer();
    CacheEntry newEntry = container.createEntry(key);
    newEntry.setPresence(CacheEntry.Presence.LOADING);

    ActorPreStartHandler.Actor loadRef = param.getCacheActor().getLoadRef();
    loadRef.tell(new RequestLoadDataMsg(key));
  }
}
