package luj.game.internal.luj.cluster;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import java.util.List;
import luj.ava.spring.Internal;
import luj.cluster.api.start.NodeStartListener;
import luj.game.api.start.GameStartListener;
import org.springframework.beans.factory.annotation.Autowired;

@Internal
final class OnLujClusterStart implements NodeStartListener {

  @Override
  public void onStart(Context ctx) {
    //TODO: 调用各个零件库初始化

    //TODO: 调用启动监听

    for (GameStartListener listener : nonNull(_startListeners)) {
      listener.onStart(null);
    }
  }

  private <T> List<T> nonNull(List<T> list) {
    return MoreObjects.firstNonNull(list, ImmutableList.of());
  }

  @Autowired(required = false)
  private List<GameStartListener> _startListeners;
}
