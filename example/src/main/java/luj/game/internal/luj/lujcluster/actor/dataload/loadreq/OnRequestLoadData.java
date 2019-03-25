package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.InvocationTargetException;
import luj.ava.spring.Internal;
import luj.cache.api.container.CacheKey;
import luj.cluster.api.actor.ActorPreStartHandler.Actor;
import luj.cluster.api.logging.Log;
import luj.game.internal.luj.lujcluster.actor.datacache.loadrsp.FinishLoadDataMsg;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActorReceive;

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

    Actor cacheActor = loadActor.getCacheRef();
    cacheActor.tell(new FinishLoadDataMsg(ImmutableList.of(
        new FinishItemImpl(dataKey, loadTemp(dataKey.getDataType())))));
  }

  private Object loadTemp(Class<?> dataType) {
    try {
      return dataType.getConstructor().newInstance();

    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new UnsupportedOperationException(e);
    }
  }
}
