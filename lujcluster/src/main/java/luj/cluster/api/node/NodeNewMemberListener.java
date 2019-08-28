package luj.cluster.api.node;

public interface NodeNewMemberListener {

  interface Context {

    Node getMemberNode();
  }

  interface Node {

    void sendMessage(String msgKey, Object msg);
  }

  void onMember(Context ctx);
}
