package luj.cache.internal.request.tree.walk;

import luj.cache.internal.request.tree.RequestTreeState;
import org.omg.CORBA.NO_IMPLEMENT;

public interface RequestTreeWalker {

  interface Factory {

    static RequestTreeWalker create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  @FunctionalInterface
  interface StepListener {

    void onStep(Node node);
  }

  interface Node {

    Object getCacheKey();
  }

  void walk(RequestTreeState tree, StepListener listener);
}
