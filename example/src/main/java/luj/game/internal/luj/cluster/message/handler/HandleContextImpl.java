package luj.game.internal.luj.cluster.message.handler;

import luj.game.api.proto.GameProtoHandler;
import org.omg.CORBA.NO_IMPLEMENT;

final class HandleContextImpl implements GameProtoHandler.Context<Object> {

   HandleContextImpl(Object proto) {
    _proto = proto;
  }

  @Override
  public Object getCurrentProto() {
    return _proto;
  }

  @Override
  public void sendProto(Object proto) {
    throw new NO_IMPLEMENT("sendProto尚未实现");
  }

  @Override
  public void fireEvent(Object event) {
    throw new NO_IMPLEMENT("fireEvent尚未实现");
  }

  private final Object _proto;
}
