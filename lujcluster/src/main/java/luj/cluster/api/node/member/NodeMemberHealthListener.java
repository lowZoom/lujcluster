package luj.cluster.api.node.member;

import java.util.List;

public interface NodeMemberHealthListener {

  interface Context {

    LocalNode getSelfNode();

    RemoteNode getMemberNode();

//    <T> T getApplicationBean();
  }

  interface LocalNode {

    void sendMessage(String msgKey, Object msg);
  }

  interface RemoteNode {

    String getId();

    List<String> getTags();

    boolean isHealthy();
  }

  void onHealth(Context ctx) throws Exception;
}
