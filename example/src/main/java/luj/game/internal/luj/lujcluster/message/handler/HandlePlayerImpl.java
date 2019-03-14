package luj.game.internal.luj.lujcluster.message.handler;

import luj.cluster.api.message.NodeMessageListener;
import luj.game.api.data.PlayerDataCommand;
import luj.game.api.proto.GameProtoHandler;
import luj.game.internal.luj.lujcluster.actor.datacache.execmd.RequestExecPlayerCmdMsg;

final class HandlePlayerImpl implements GameProtoHandler.Player {

  HandlePlayerImpl(NodeMessageListener.Actor dataActor, String playerId) {
    _dataActor = dataActor;
    _playerId = playerId;
  }

  @Override
  public void executeDataCommand(Class<? extends PlayerDataCommand<?>> cmdType) {
    //TODO: 执行在receiveActor里，需要tell给dataActor
    _dataActor.tell(new RequestExecPlayerCmdMsg(_playerId, cmdType));
  }

  private final NodeMessageListener.Actor _dataActor;

  private final String _playerId;
}
