package luj.game.internal.luj.lujcache;

import luj.cache.api.listener.CacheMissListener;
import luj.game.internal.luj.lujcluster.actor.dataload.loadreq.RequestLoadDataMsg;

final class OnLujcacheMiss implements CacheMissListener<LujcacheKey> {

  @Override
  public void onMiss(Context ctx) {
    //TODO: 怎么拿到 loadactor 用于后面发送消息

    JambeanInLujcache appBean = ctx.getApplicationBean();

    //TODO: 发送 读取数据 消息

    LujcacheKey cacheKey = ctx.getKey(this);
    appBean.getLoadActor().tell(new RequestLoadDataMsg(cacheKey));

  }
}
