package luj.game.internal.luj.lujcache;

import luj.cache.api.listener.CacheMissListener;

final class OnLujcacheMiss implements CacheMissListener<LujcacheKey> {

  @Override
  public void onMiss(Context ctx) {
    //TODO: 怎么拿到 loadactor 用于后面发送消息

    Object appBean = ctx.getApplicationBean();

    //TODO: 发送 读取数据 消息


  }
}
