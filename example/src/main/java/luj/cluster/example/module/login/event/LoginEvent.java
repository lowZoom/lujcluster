package luj.cluster.example.module.login.event;

public class LoginEvent {

  public LoginEvent(String charUid) {
    _charUid = charUid;
  }

  public String getCharUid() {
    return _charUid;
  }

  private final String _charUid;
}
