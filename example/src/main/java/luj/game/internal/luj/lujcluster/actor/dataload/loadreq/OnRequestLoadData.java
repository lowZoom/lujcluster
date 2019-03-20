package luj.game.internal.luj.lujcluster.actor.dataload.loadreq;

import com.google.common.collect.ImmutableList;
import luj.ava.spring.Internal;
import luj.cluster.api.message.NodeMessageListener.Actor;
import luj.game.internal.luj.lujcluster.actor.datacache.loadrsp.FinishLoadDataMsg;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActor;
import luj.game.internal.luj.lujcluster.actor.dataload.DataLoadActorReceive;

@Internal
final class OnRequestLoadData implements DataLoadActorReceive<RequestLoadDataMsg> {

  @Override
  public void onHandle(Context ctx) {

    RequestLoadDataMsg msg = ctx.getMessage(this);

    //TODO: 先当成数据库不存在该数据继续写逻辑

    DataLoadActor loadActor = ctx.getActor(this);

    Actor cacheActor = loadActor.getCacheActor();
    cacheActor.tell(new FinishLoadDataMsg(ImmutableList.of()));
  }
}
