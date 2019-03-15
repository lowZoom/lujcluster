package luj.game.internal.luj.lujcluster.actor.datacache.loadrsp;

import java.util.List;
import luj.game.internal.luj.lujcache.LujcacheKey;

public class FinishLoadDataMsg {

  interface FinishItem {

    LujcacheKey getKey();

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
