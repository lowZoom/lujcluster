package luj.game.internal.luj.lujcluster.actor.datacache.loadrsp;

import static com.google.common.base.Preconditions.checkState;

import luj.cache.api.container.CacheContainer;
import luj.cache.api.container.CacheEntry;
import luj.cache.api.container.CacheEntry.Presence;
import luj.cache.api.container.CacheKey;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import org.springframework.stereotype.Component;

@Component
final class OnFinishLoadData implements DataActorMsgHandler<FinishLoadDataMsg> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState actor = ctx.getActor(this);
    CacheContainer cache = actor.getDataCache();

    FinishLoadDataMsg msg = ctx.getMessage(this);

    for (FinishLoadDataMsg.FinishItem item : msg.getItemList()) {
      CacheKey key = item.getKey();

      CacheEntry entry = cache.getEntry(key);
      checkState(entry.getPresence() == Presence.LOADING);

      updateEntry(entry, item);
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
