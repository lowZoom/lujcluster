package luj.cluster.api.node;

import java.util.Set;

/**
 * 当集群中有新成员加入时触发
 */
public interface NodeNewMemberListener {

  interface Context {

    Node getSelfNode();

    NodeRemote getMemberNode();

    <T> T getApplicationBean();
  }

  interface Node {

    void sendMessage(String msgKey, Object msg);
  }

  interface NodeRemote extends Node {

    //TODO: 看看是否真的需要
    NodeType getType();

    Set<String> getTags();
  }

  void onMember(Context ctx) throws Exception;
}
