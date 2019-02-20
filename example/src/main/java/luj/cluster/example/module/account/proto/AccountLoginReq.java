package luj.cluster.example.module.account.proto;

public class AccountLoginReq {

  public AccountLoginReq(String account) {
    _account = account;
  }

  public String getAccount() {
    return _account;
  }

  private final String _account;
}
