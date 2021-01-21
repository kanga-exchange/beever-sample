package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.Input;
import io.beever.common.http.HttpRequestHandlerAbstract;
import io.vertx.reactivex.core.Vertx;

@SuppressWarnings("unused")
@Input({"$url", "?*content", "?*headers", "?#timeout", "?&trustAll", "?$method"})
public class InternalHttpRequestHandler extends HttpRequestHandlerAbstract implements BeeverHandler {

	public InternalHttpRequestHandler(Vertx vertx) {
		super(vertx);
	}
}
