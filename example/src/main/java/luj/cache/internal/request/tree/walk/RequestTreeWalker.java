package luj.cache.internal.request.tree.walk;

import luj.cache.internal.request.tree.RequestTree;
import org.omg.CORBA.NO_IMPLEMENT;

public interface RequestTreeWalker {

  interface Factory {

    static RequestTreeWalker create() {
      throw new NO_IMPLEMENT("create尚未实现");
    }
  }

  @FunctionalInterface
  interface StepListener {

    void onStep(StepContext ctx);
  }

  interface StepContext {

    RequestTree.Node getNode();
  }

  void walk(RequestTree tree, StepListener listener);
}
