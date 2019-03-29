package luj.cluster.api.node;

public interface NodeNewMemberListener {

  interface Context {

  }

  void onMember(Context ctx);
}
