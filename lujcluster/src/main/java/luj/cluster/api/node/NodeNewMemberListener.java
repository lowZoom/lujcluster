package luj.cluster.api.node;

/**
 * 当集群中有新成员加入时触发
 */
public interface NodeNewMemberListener {

  interface Context {

    Node getMemberNode();

    <T> T getApplicationBean();
  }

  interface Node {

    void sendMessage(String msgKey, Object msg);
  }

  void onMember(Context ctx) throws Exception;
}
