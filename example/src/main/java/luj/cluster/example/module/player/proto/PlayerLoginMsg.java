package luj.cluster.example.module.player.proto;

public class PlayerLoginMsg {

  public PlayerLoginMsg(String charUid) {
    _charUid = charUid;
  }

  public String getCharUid() {
    return _charUid;
  }

  private final String _charUid;
}
