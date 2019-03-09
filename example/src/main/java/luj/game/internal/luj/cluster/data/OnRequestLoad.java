package luj.game.internal.luj.cluster.data;

import luj.game.internal.luj.cluster.data.loadreq.RequestLoadMsg;
import org.springframework.stereotype.Component;

@Component
final class OnRequestLoad implements DataActorMsgHandler<RequestLoadMsg> {

  @Override
  public void onHandle(Context ctx) {
    DataActorState actor = ctx.getActor(this);

    RequestLoadMsg msg = ctx.getMessage(this);
  }
}
