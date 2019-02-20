package luj.cluster.example.module.login.proto;

public class LoginMsg {

  public LoginMsg(String charUid) {
    _charUid = charUid;
  }

  public String getCharUid() {
    return _charUid;
  }

  private final String _charUid;
}
