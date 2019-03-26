package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

import com.google.common.collect.ImmutableList;
import luj.ava.spring.Internal;
import luj.cache.api.container.CacheKey;
import luj.cluster.api.actor.ActorPreStartHandler;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.datacache.loadrsp.FinishLoadDataMsg;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActorReceive;
import luj.persist.api.PersistSession;

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
    cacheActor.tell(new FinishLoadDataMsg(ImmutableList
        .of(loadTemp(dataKey, loadActor.getLujpersist()))));
  }

  private FinishItemImpl loadTemp(CacheKey<?> dataKey, PersistSession lujpersist) {
    Object data = lujpersist.createData(dataKey.getDataType());
    return new FinishItemImpl(dataKey, data);
  }
}
