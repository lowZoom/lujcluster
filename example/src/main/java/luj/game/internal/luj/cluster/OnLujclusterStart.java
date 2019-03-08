package luj.game.internal.luj.cluster;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.start.NodeStartListener;
import luj.game.api.proto.GameProtoHandler;
import luj.game.api.start.GameStartListener;
import luj.game.internal.luj.cluster.message.handler.collect.MessageHandlerCollector;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class OnLujclusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) throws Exception {
    //TODO: 调用各个零件库初始化

    //TODO: 初始化cluster消息处理注册
    ctx.registerMessageHandler(MessageHandlerCollector.Factory.create(_protoHandlerList).collect());

    StartContextImpl startCtx = new StartContextImpl(ctx);
    for (GameStartListener listener : nonNull(_startListenerList)) {
      listener.onStart(startCtx);
    }
  }

  private <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<GameStartListener> _startListenerList;


  @Autowired(required = false)
  private List<GameProtoHandler<?>> _protoHandlerList;
}
