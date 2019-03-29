package luj.game.internal.luj.lujcluster.actor.gameplay.data.load.loadreq;

import com.google.common.collect.ImmutableList;
import luj.ava.spring.Internal;
import luj.cache.api.container.CacheKey;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.loadrsp.FinishLoadDataMsg;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.load.DataLoadActor;
import luj.game.internal.luj.lujcluster.actor.gameplay.data.load.DataLoadActorReceive;

@Internal
final class OnRequestLoadData implements DataLoadActorReceive<RequestLoadDataMsg> {

  @Override
  public void onHandle(Context ctx) {
    Log log = ctx.getLogger();

    RequestLoadDataMsg msg = ctx.getMessage(this);
    CacheKey<?> dataKey = msg.getDataKey();
    log.debug("读取数据：{}", dataKey);

    //TODO: 先当成数据库不存在该数据继续写逻辑

    DataLoadActor loadActor = ctx.getActor(this);

    ActorPreStartHandler.Actor cacheActor = loadActor.getCacheRef();
    cacheActor.tell(new FinishLoadDataMsg(ImmutableList.of(loadTemp(dataKey))));
  }

  private FinishItemImpl loadTemp(CacheKey<?> dataKey) {
    try {
      Class<?> dataType = Class.forName(dataKey.getDataType().getName() + "Impl");
      Object data = dataType.getConstructor().newInstance();
      return new FinishItemImpl(dataKey, data);

    } catch (Exception e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
