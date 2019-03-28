package luj.cache.internal.request.request.result;

import java.util.List;

final class RequestSuccessFinisherImpl implements RequestSuccessFinisher {

  RequestSuccessFinisherImpl(Request reqRoot) {
    _reqRoot = reqRoot;
  }

  @Override
  public void finish() {
    walkReq(_reqRoot);

    _reqRoot.fireReady();
  }

  private void walkReq(Request req) {
    Field field = req.getResultField();
    if (field != null) {
      field.fill();
    }

    for (Request child : req.getChildren()) {
      walkReq(child);
    }
  }

  interface Request {

    Field getResultField();

    List<Request> getChildren();

    void fireReady();
  }

  interface Field {

    void fill();
  }

  private final Request _reqRoot;
}
