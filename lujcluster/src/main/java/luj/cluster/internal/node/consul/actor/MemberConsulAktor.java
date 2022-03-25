package luj.cluster.internal.node.consul.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import com.ecwid.consul.v1.ConsulClient;
import io.grpc.Server;
import java.util.concurrent.ExecutorService;
import luj.cluster.api.node.member.NodeMemberHealthListener;
import luj.cluster.api.node.member.NodeNewMemberListener;
import luj.cluster.internal.node.consul.actor.message.StartConsulMsg;
import luj.cluster.internal.node.member.actor.NodeMemberAktor;

public class MemberConsulAktor extends AbstractActor {

  public static Props props(NodeMemberAktor actor) {
    NodeNewMemberListener joinListener = actor.getJoinListener();
    NodeMemberHealthListener healthListener = actor.getHealthListener();

    ActorRef receiveRef = actor.getReceiveRef();
    ActorRef memberRef = actor.self();

    return Props.create(MemberConsulAktor.class, () ->
        new MemberConsulAktor(joinListener, healthListener, receiveRef, memberRef));
  }

  MemberConsulAktor(NodeNewMemberListener joinListener,
      NodeMemberHealthListener healthListener, ActorRef receiveRef, ActorRef memberRef) {
    _joinListener = joinListener;
    _healthListener = healthListener;
    _receiveRef = receiveRef;
    _memberRef = memberRef;
  }

  @Override
  public void postStop() {
    _watchExec.shutdown();
    _consul.agentServiceDeregister(_selfName);

    // 从consul移除后再关rpc
    _grpc.shutdown();
  }

  @Override
  public Receive createReceive() {
    return receiveBuilder()
        .match(StartConsulMsg.class, new OnStartConsul(this))
        .build();
  }

  public String getSelfName() {
    return _selfName;
  }

  public void setSelfName(String selfName) {
    _selfName = selfName;
  }

  public Server getGrpc() {
    return _grpc;
  }

  public void setGrpc(Server grpc) {
    _grpc = grpc;
  }

  public ConsulClient getConsul() {
    return _consul;
  }

  public void setConsul(ConsulClient consul) {
    _consul = consul;
  }

  public void setWatchExec(ExecutorService watchExec) {
    _watchExec = watchExec;
  }

  public NodeNewMemberListener getJoinListener() {
    return _joinListener;
  }

  public NodeMemberHealthListener getHealthListener() {
    return _healthListener;
  }

  public ActorRef getReceiveRef() {
    return _receiveRef;
  }

  public ActorRef getMemberRef() {
    return _memberRef;
  }

  private String _selfName;
  private Server _grpc;

  private ConsulClient _consul;
  private ExecutorService _watchExec;

  private final NodeNewMemberListener _joinListener;
  private final NodeMemberHealthListener _healthListener;

  private final ActorRef _receiveRef;
  private final ActorRef _memberRef;
}
