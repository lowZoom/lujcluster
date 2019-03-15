package luj.game.internal.luj.lujcluster.actor.datacache.reqexec;

public class RequestExecPlayerCmdMsg {

  public RequestExecPlayerCmdMsg(String playerId, Class<?> cmdType) {
    _playerId = playerId;
    _cmdType = cmdType;
  }

  public String getPlayerId() {
    return _playerId;
  }

  public Class<?> getCmdType() {
    return _cmdType;
  }

  private final String _playerId;

  private final Class<?> _cmdType;
}
