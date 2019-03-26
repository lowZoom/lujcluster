package luj.game.internal.luj.lujcluster;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.node.NodeStartListener;
import luj.game.api.proto.GameProtoHandler;
import luj.game.api.start.GameStartListener;
import luj.game.internal.data.DataCmdCollectBean;
import luj.game.internal.data.collect.DataCmdMapCollector;
import luj.game.internal.luj.lujcluster.actor.datacache.DataActorState;
import luj.game.internal.luj.lujcluster.message.handler.collect.MessageHandlerCollector;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) throws Exception {
    //TODO: 调用各个零件库初始化
    JambeanInLujcluster param = ctx.getStartParam();

    //TODO: 收集数据loader
    ctx.createApplicationActor(dataCache(param));

    //TODO: 初始化cluster消息处理注册
    ctx.registerMessageHandler(MessageHandlerCollector.Factory.create(_protoHandlerList).collect());

    StartContextImpl startCtx = new StartContextImpl(ctx);
    for (GameStartListener listener : nonNull(_startListenerList)) {
      listener.onStart(startCtx);
    }
  }

  private DataActorState dataCache(JambeanInLujcluster param) {
    return new DataActorState(param.getLujcache().createCache(null),
        DataCmdMapCollector.Factory.create(_dataCmdCollectBean).collect());
  }

  private <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<GameStartListener> _startListenerList;

  @Autowired(required = false)
  private List<GameProtoHandler<?>> _protoHandlerList;

  @Autowired
  private DataCmdCollectBean _dataCmdCollectBean;
}
