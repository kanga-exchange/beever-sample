package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.GET;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;

@GET
public class PublicPingHandler implements BeeverHandler {
    @Override
    public Single<JsonObject> handle(JsonObject jsonObject) {
        return Single.just(JsonHelper.toJson(
            "result", "ok",
            "ts", LocalDateTime.now(),
            "version", "1.0"
        ));
    }
}
