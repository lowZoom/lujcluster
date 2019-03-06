package luj.cluster.example.module.player.event;

public class PlayerLoginEvent {

  public PlayerLoginEvent(String charUid) {
    _charUid = charUid;
  }

  public String getCharUid() {
    return _charUid;
  }

  private final String _charUid;
}
