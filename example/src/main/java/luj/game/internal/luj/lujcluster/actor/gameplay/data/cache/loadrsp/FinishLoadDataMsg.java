package luj.game.internal.luj.lujcluster.actor.gameplay.data.cache.loadrsp;

import java.util.List;
import luj.cache.api.container.CacheKey;

public class FinishLoadDataMsg {

  public interface FinishItem {

    CacheKey getKey();

    boolean isPresent();

    Object getData();
  }

  public FinishLoadDataMsg(List<FinishItem> itemList) {
    _itemList = itemList;
  }

  public List<FinishItem> getItemList() {
    return _itemList;
  }

  private final List<FinishItem> _itemList;
}
