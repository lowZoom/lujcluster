package luj.game.internal.luj.lujcache;

import luj.ava.spring.Internal;
import luj.cache.api.container.CacheKey;
import luj.cache.api.listener.CacheMissListener;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.dataload.loadreq.RequestLoadDataMsg;

@Internal
final class OnLujcacheMiss implements CacheMissListener {

  @Override
  public void onMiss(Context ctx) {
    JamreqInLujcache param = ctx.getRequestParam();
    CacheKey cacheKey = ctx.getMissingKey();

    Log log = param.getLogger();
    log.debug("缓存项miss，key：{}", cacheKey);

    //TODO: 发送 读取数据 消息
    param.getLoadActor().tell(new RequestLoadDataMsg(cacheKey));
  }
}
