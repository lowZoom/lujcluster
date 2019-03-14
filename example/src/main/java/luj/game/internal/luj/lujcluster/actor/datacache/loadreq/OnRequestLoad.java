package luj.game.internal.luj.lujcluster.actor.datacache.loadreq;

import luj.game.internal.luj.lujcluster.actor.datacache.DataActorMsgHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import org.springframework.stereotype.Component;

@Component
final class OnRequestLoad implements DataActorMsgHandler<RequestLoadMsg> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState actor = ctx.getActor(this);

    RequestLoadMsg msg = ctx.getMessage(this);
  }
}
