package luj.cluster.internal.node.message.receive.message;

import java.util.Map;

public class RegisterReceiveMsg {

  public RegisterReceiveMsg(Map<String, ?> registerMap) {
    _registerMap = registerMap;
  }

  public Map<String, ?> getRegisterMap() {
    return _registerMap;
  }

  private final Map<String, ?> _registerMap;
}
