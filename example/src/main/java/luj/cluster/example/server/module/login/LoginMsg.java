package luj.cluster.example.server.module.login;

public class LoginMsg {

  public LoginMsg(String account) {
    _account = account;
  }

  public String getAccount() {
    return _account;
  }

  private final String _account;
}
