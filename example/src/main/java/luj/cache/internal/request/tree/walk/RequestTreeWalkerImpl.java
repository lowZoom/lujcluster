package luj.cache.internal.request.tree.walk;

import luj.cache.internal.request.tree.RequestTreeState;

final class RequestTreeWalkerImpl implements RequestTreeWalker {

  RequestTreeWalkerImpl(TreeNode rootNode, StepListener stepListener) {
    _rootNode = rootNode;
    _stepListener = stepListener;
  }

  @Override
  public void walk(RequestTreeState tree, StepListener listener) {
    walkImpl(_rootNode);
  }

  private void walkImpl(TreeNode node) {
  }

  interface TreeNode {

  }

  private final TreeNode _rootNode;

  private final StepListener _stepListener;
}
