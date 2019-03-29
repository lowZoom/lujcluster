package luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.loadrsp;

import static com.google.common.base.Preconditions.checkState;

import luj.ava.spring.Internal;
import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheEntry.Presence;
import luj.cache.api.container.CacheKey;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.DataActorState;

@Internal
final class OnFinishLoadData implements DataActorMsgHandler<FinishLoadDataMsg> {

  @Override
  public void onHandle(Context ctx) {
    Log log = ctx.getLogger();

    DataActorState actor = ctx.getActor(this);
    CacheContainer cache = actor.getDataCache();

    FinishLoadDataMsg msg = ctx.getMessage(this);

    for (FinishLoadDataMsg.FinishItem item : msg.getItemList()) {
      CacheKey key = item.getKey();

      CacheEntry entry = cache.getEntry(key);
      checkState(entry.getPresence() == Presence.LOADING);

      updateEntry(entry, item);
      log.debug("缓存项数据，key:{}，结果：{}", key, entry.getPresence());
    }

    cache.getRequestQueue().wake();
  }

  private void updateEntry(CacheEntry entry, FinishLoadDataMsg.FinishItem result) {
    if (!result.isPresent()) {
      entry.setPresence(Presence.ABSENT);
      return;
    }

    Object data = result.getData();
    entry.setData(data);
    entry.setPresence(Presence.PRESENT);
  }
}
