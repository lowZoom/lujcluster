package luj.game.internal.luj.lujcache;

import luj.cache.api.container.CacheMissListener;

final class OnLujcacheMiss implements CacheMissListener {

  @Override
  public void onMiss() {
    //TODO: 发送 读取数据 消息
  }
}
