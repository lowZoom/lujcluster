package luj.cache.internal.request.request.result;

import luj.cache.api.listener.RequestResultFactory;
import luj.cache.internal.request.CacheRequestImpl;
import luj.cache.internal.request.request.Requesting;

public interface RequestSuccessFinisher {

  interface Factory {

    static RequestSuccessFinisher create(CacheRequestImpl request, Requesting requesting) {

      RequestResultFactory resultFactory = requesting.getContainerState().getBeanCollect().getRequestResultFactory();
      Class<?> resultType = request.getResultType();

      ResultFactoryContextImpl ctx = new ResultFactoryContextImpl(resultType);
      Object reqResult = resultFactory.create(ctx);

      return new RequestSuccessFinisherImpl(new RequestImpl(reqResult, request.getRootNode(), requesting));
    }
  }

  void finish();
}
